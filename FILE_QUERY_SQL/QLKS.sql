USE [master]
GO

/****** Object:  Database [QLKS]    Script Date: 11/5/2023 2:51:19 PM ******/
CREATE DATABASE [QLKS]
 ON  PRIMARY 
( NAME = N'QLKS', FILENAME = N'T:\SQL_SERVER\MSSQL16.MSSQLSERVER\MSSQL\DATA\QLKS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QLKS_log', FILENAME = N'T:\SQL_SERVER\MSSQL16.MSSQLSERVER\MSSQL\DATA\QLKS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO

use QLKS 

CREATE TABLE NhanVien(
	MaNV nvarchar(20) not null PRIMARY KEY,
	TenNV nvarchar(30) not null,
	SĐT nvarchar(20) not null,
	DiaChi nvarchar(50) not null,
	Email nvarchar(30) not null
)

CREATE TABLE LoaiKhach(
	MaLoai nvarchar(20) not null PRIMARY KEY,
	TenLoai nvarchar(20),
	KhauTru float not null
)

CREATE TABLE KhachHang(
	CCCD nvarchar(13) not null PRIMARY KEY,
	STK nvarchar(20) not null,
	HoTen nvarchar(30) not null,
	SĐT nvarchar(20) not null,
	DiaChi nvarchar(50) not null,
	Email nvarchar(30) not null,
	MaLoaiKH nvarchar(20) not null FOREIGN KEY REFERENCES LoaiKhach(MaLoai)
)

CREATE TABLE LoaiPhong(
	MaLoai nvarchar(20) not null PRIMARY KEY,
	TenLoai nvarchar(20),
	Gia int not null
)

CREATE TABLE Phong(
	SoPhong int not null PRIMARY KEY,
	TenPhong nvarchar(20),
	LoaiPhong nvarchar(20) not null FOREIGN KEY REFERENCES LoaiPhong(MaLoai),
	IsEmpty bit not null,
)

CREATE TABLE PhieuDatPhong(
	MaPhieuDat nvarchar(20) not null PRIMARY KEY,
	MaNV nvarchar(20) not null FOREIGN KEY REFERENCES NhanVien(MaNV),
	CCCD nvarchar(13) not null FOREIGN KEY REFERENCES KhachHang(CCCD),
	SoPhong int not null FOREIGN KEY REFERENCES Phong(SoPhong),
	SoLuongPhong int not null,
	NgayDen date not null,
	NgayDi date not null,
	CONSTRAINT CHK_Ngay CHECK (NgayDi >= NgayDen)
)

CREATE TABLE PhieuNhanPhong(
	MaPhieuNhan nvarchar(20) not null PRIMARY KEY,
	MaPhieuDat nvarchar(20) not null FOREIGN KEY REFERENCES PhieuDatPhong(MaPhieuDat),
	GioNhan datetime not null,
	NgayNhan datetime not null,
)

CREATE TABLE DichVu(
	MaDV nvarchar(20) not null PRIMARY KEY,
	TenDV nvarchar(30) not null,
	GiaDV int not null
)

CREATE TABLE HoaDon(
	MaHoaDon nvarchar(20) not null PRIMARY KEY,
	MaPhieu nvarchar(20) not null FOREIGN KEY REFERENCES PhieuNhanPhong(MaPhieuNhan),
	TongTien int not null
)
CREATE TABLE ChiTietHoaDon(
	MaHoaDon nvarchar(20) not null FOREIGN KEY REFERENCES HoaDon(MaHoaDon),
	MaDV nvarchar(20) not null FOREIGN KEY REFERENCES DichVu(MaDV),
	NgayTra date not null,
	GioTra datetime not null
	CONSTRAINT PK_ChiTietHoaDon PRIMARY KEY (MaHoaDon,MaDV)
)

INSERT INTO LoaiKhach(MaLoai,TenLoai,KhauTru) 
VALUES (N'V',N'Khách Quen',0.3),
		(N'N',N'Khách Thường',0)

INSERT INTO KhachHang(CCCD,HoTen,SĐT,STK,DiaChi,Email,MaLoaiKH)
VALUES (N'019283940112',N'Nguyễn Đức Cường',N'0384851290',N'500030001203',N'Q.12,Hồ Chí Minh',N'duccuong16092003@gmail.com','V'),
	   (N'219233940122',N'Đào Huy Hoàng',N'0183451290',N'533020101203',N'Q.Tân Bình,Hồ Chí Minh',N'hoang11@gmail.com','V'),
	   (N'362233213322',N'Đặng Nguyễn Minh Thiện',N'0231345129',N'132020101233',N'Q.Gò Vấp,Hồ Chí Minh',N'thien456@gmail.com','N')

INSERT INTO NhanVien(MaNV,TenNV,SĐT,DiaChi,Email)
VALUES ('NV001',N'Nguyễn Văn An',N'0127894391',N'Q.Gò Vấp','vanan1111@gmail.com'),
		('NV002',N'Nguyễn Văn Linh',N'0235824391',N'Q.Gò Vấp','vanminh1214@gmail.com')

INSERT INTO LoaiPhong(MaLoai,TenLoai,Gia)
VALUES (N'PV',N'Phòng Vip',500000),
		(N'PN',N'Phòng Thường',250000)

INSERT INTO Phong(SoPhong,TenPhong,LoaiPhong,IsEmpty)
VALUES (1,'T1.01','PV',0),
	   (2,'T1.02','PV',0),
	   (3,'T1.03','PN',0),
	   (4,'T1.04','PV',1),
	   (5,'T1.05','PN',1)

INSERT INTO PhieuDatPhong(MaPhieuDat,MaNV,CCCD,SoPhong,SoLuongPhong,NgayDen,NgayDi)
VALUES ('PD001','NV001',N'019283940112',1,1,'2023-5-11','2023-5-15'),
	   ('PD002','NV002',N'219233940122',2,1,'2023-5-12','2023-5-14'),
	   ('PD003','NV001',N'362233213322',3,1,'2023-5-4','2023-5-9')

INSERT INTO PhieuNhanPhong(MaPhieuNhan,MaPhieuDat,GioNhan,NgayNhan)
VALUES ('PN001','PD001','2023-5-12 10:34:09 AM','2023-5-12'),
	   ('PN002','PD002','2023-5-13 5:34:19 AM','2023-5-13'),
	   ('PN003','PD003','2023-5-5 9:44:19 PM','2023-5-5')

INSERT INTO DichVu(MaDV,TenDV,GiaDV)
VALUES ('DV001',N'Dịch vụ Karaoke',300000),
	   ('DV002',N'Dịch vụ Trông Trẻ',150000),
	   ('DV003',N'Dịch vụ Thuê Xe',400000)

INSERT INTO HoaDon(MaHoaDon,MaPhieu,TongTien)
VALUES ('HD001','PN001',1300000),
	   ('HD002','PN002',1300000),
	   ('HD003','PN003',800000)

INSERT INTO ChiTietHoaDon(MaHoaDon,MaDV,NgayTra,GioTra)
VALUES ('HD001','DV001','2023-5-14 10:34:09 AM','2023-5-14'),
	   ('HD002','DV001','2023-5-15 10:34:09 AM','2023-5-15'),
	   ('HD003','DV001','2023-5-7 10:34:09 AM','2023-5-7')

--select HoaDon.MaHoaDon,MaPhieu,TongTien,ChiTietHoaDon.MaDV,NgayTra,GioTra,TenDV,NgayNhan,GioNhan,KhachHang.CCCD,HoTen,DiaChi,KhachHang.SĐT from HoaDon join ChiTietHoaDon on HoaDon.MaHoaDon = ChiTietHoaDon.MaHoaDon
--join DichVu on ChiTietHoaDon.MaDV = DichVu.MaDV join PhieuNhanPhong on PhieuNhanPhong.MaPhieuNhan = HoaDon.MaPhieu
--join PhieuDatPhong on PhieuNhanPhong.MaPhieuDat = PhieuDatPhong.MaPhieuDat
--join KhachHang on KhachHang.CCCD = PhieuDatPhong.CCCD