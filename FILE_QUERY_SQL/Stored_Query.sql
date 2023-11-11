USE [QLKS]
GO

/****** Object:  StoredProcedure [dbo].[getListTrangThaiPhong]    Script Date: 11/9/2023 5:37:08 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--------------------------------------------------------------------------------------
CREATE PROCEDURE [dbo].[getListTrangThaiPhong] 
	@trangThai int
AS
BEGIN
	SELECT * FROM Phong where IsEmpty = @trangThai
END
GO

USE [QLKS]
GO

/****** Object:  StoredProcedure [dbo].[getListPhongChuaTra]    Script Date: 11/9/2023 5:37:37 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[getListPhongChuaTra] 
AS 
BEGIN 
	select ChiTietDatPhong.MaPhieuDat from Phong join ChiTietDatPhong 
	on Phong.SoPhong = ChiTietDatPhong.SoPhong JOIN PhieuDatPhong 
		ON PhieuDatPhong.MaPhieuDat = ChiTietDatPhong.MaPhieuDat join PhieuNhanPhong
			on PhieuNhanPhong.MaPhieuDat = PhieuDatPhong.MaPhieuDat 
	where MaPhieuNhan not in (select MaPhieu from HoaDon) AND IsEmpty = 0
END
GO
-----------------------------------------------------------------------------------
USE [QLKS]
GO

/****** Object:  StoredProcedure [dbo].[getListPhongChuaNhan]    Script Date: 11/9/2023 5:38:10 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[getListPhongChuaNhan]

AS
BEGIN
	SELECT *
	FROM PhieuDatPhong join ChiTietDatPhong 
			on PhieuDatPhong.MaPhieuDat = ChiTietDatPhong.MaPhieuDat join Phong 
				on Phong.SoPhong = ChiTietDatPhong.SoPhong
	WHERE NOT EXISTS (
		SELECT 1
		FROM PhieuNhanPhong
		WHERE PhieuDatPhong.MaPhieuDat = PhieuNhanPhong.MaPhieuDat
	)AND Phong.IsEmpty = 0
END
GO


--------------------------------------------------------------------------------------------------------
USE [QLKS]
GO

/****** Object:  StoredProcedure [dbo].[getListPhongByMaPhieuDat]    Script Date: 11/9/2023 5:38:27 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[getListPhongByMaPhieuDat] @maPhieuDat nvarchar(20)
AS 
BEGIN
	select ChiTietDatPhong.SoPhong , TenPhong, LoaiPhong, IsEmpty
from PhieuDatPhong join ChiTietDatPhong 
	on PhieuDatPhong.MaPhieuDat = ChiTietDatPhong.MaPhieuDat join Phong 
		on ChiTietDatPhong.SoPhong = Phong.SoPhong
where PhieuDatPhong.MaPhieuDat = @maPhieuDat
END
GO


CREATE PROCEDURE insertPhieuDat @maPhieuDat nvarchar(20), @maNV nvarchar(20) , @CCCD nvarchar(13), @soLuong int, @ngayDen date, @ngayDi date
AS
BEGIN
	INSERT INTO PhieuDatPhong(MaPhieuDat, MaNV, CCCD, NgayDen, NgayDi,soLuong)
VALUES	
		(@maPhieuDat, @maNV, @CCCD, @ngayDen, @ngayDi,@soLuong)
END
GO


CREATE PROCEDURE insertChiTietDatPhong @maPhieuDat nvarchar(20), @soPhong int
AS
BEGIN
	INSERT INTO ChiTietDatPhong (MaPhieuDat, SoPhong)
	VALUES	
			(@maPhieuDat,@soPhong)
END
GO

CREATE PROCEDURE updateNgayDi @maPhieuDat nvarchar(20), @ngayDi date
AS
BEGIN
	UPDATE PhieuDatPhong 
	SET NgayDi = @ngayDi
	where MaPhieuDat = @maPhieuDat
END
GO

CREATE PROCEDURE updatePhieuDat @maPhieuDat nvarchar(20), @maNV nvarchar(20) , @CCCD nvarchar(13), @soLuong int, @ngayDen date, @ngayDi date
AS
BEGIN
	UPDATE PhieuDatPhong 
	SET NgayDi = @ngayDi,
		MaNV = @maNV, 
		CCCD = @CCCD, 
		NgayDen = @ngayDen, 
		soLuong = @soLuong
	where MaPhieuDat = @maPhieuDat
END
GO


CREATE PROCEDURE insertPhieuNhan @maPhieuNhan nvarchar(20) ,@maPhieuDat nvarchar(20) ,	@gioNhan datetime ,	@ngayNhan datetime 
AS
BEGIN
	
	INSERT INTO PhieuNhanPhong (MaPhieuNhan, MaPhieuDat, GioNhan, NgayNhan)
	VALUES
			(@maPhieuNhan,@maPhieuDat , @gioNhan, @ngayNhan)
END
GO



CREATE PROCEDURE updatePhieuNhan @maPhieuNhan nvarchar(20) ,@maPhieuDat nvarchar(20) ,	@gioNhan datetime ,	@ngayNhan datetime 
AS
BEGIN
	UPDATE PhieuNhanPhong 
	SET MaPhieuDat = @maPhieuDat, 
		GioNhan = @gioNhan, 
		NgayNhan = @ngayNhan
	where MaPhieuNhan = @maPhieuNhan
	
END
GO


CREATE PROCEDURE updateTrangThaiPhong @soPhong int,@isEmpty bit
AS
BEGIN
	UPDATE Phong
	SET IsEmpty =  @isEmpty
	where SoPhong = @soPhong
END
GO


--update KhachHang

CREATE PROCEDURE updateKhachHang @CCCD nvarchar(13) ,
	@STK nvarchar(20) ,
	@hoTen nvarchar(30),
	@SĐT nvarchar(20) ,
	@diaChi nvarchar(50) ,
	@email nvarchar(30) ,
	@maLoaiKH nvarchar(20) 
AS
BEGIN
	UPDATE KhachHang
	SET HoTen = @hoTen,
		STK = @STK,
		SDT = @SĐT,
		DiaChi = @diaChi,
		Email = @email,
		MaLoaiKH = @maLoaiKH
	where CCCD = @CCCD
END
GO



CREATE PROCEDURE insertKhachHang @CCCD nvarchar(13) ,
	@STK nvarchar(20) ,
	@hoTen nvarchar(30),
	@SĐT nvarchar(20) ,
	@diaChi nvarchar(50) ,
	@email nvarchar(30) ,
	@maLoaiKH nvarchar(20) 
AS
BEGIN
	INSERT INTO KhachHang(CCCD,HoTen,SDT,STK,DiaChi,Email,MaLoaiKH) VALUES (@CCCD,@hoTen,@SĐT,@STK,@diaChi,@email,@maLoaiKH)
END
GO


CREATE PROCEDURE insertNhanVien @maNV nvarchar(20),
	@tenNV nvarchar(30),
	@SDT nvarchar(20) ,
	@diaChi nvarchar(50),
	@email nvarchar(30) 
AS
BEGIN
	INSERT INTO NhanVien(MaNV,TenNV,SĐT,DiaChi,Email)
		VALUES	(@maNV,@tenNV,@SDT,@diaChi,@email)
END
GO

CREATE PROCEDURE updateNhanVien @maNV nvarchar(20),
	@tenNV nvarchar(30),
	@SĐT nvarchar(20) ,
	@diaChi nvarchar(50),
	@email nvarchar(30) 
AS
BEGIN
	UPDATE NhanVien
	SET TenNV = @tenNV,
		SĐT = @SĐT,
		DiaChi = @diaChi,
		Email = @email
	where MaNV = @maNV
END
GO