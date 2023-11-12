USE [master]
GO

/****** Object:  Database [QLKS]    Script Date: 11/5/2023 2:51:19 PM ******/
CREATE DATABASE [QLKS]
 ON  PRIMARY 
( NAME = N'QLKS', FILENAME = N'T:\QuanLyKhachSan\QLKS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QLKS_log', FILENAME = N'T:\QuanLyKhachSan\QLKS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
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
	soLuong int not null,
	NgayDen date not null,
	NgayDi date not null,
	CONSTRAINT CHK_Ngay CHECK (NgayDi >= NgayDen)
)
CREATE TABLE ChiTietDatPhong(
	MaPhieuDat nvarchar(20) not null FOREIGN KEY REFERENCES PhieuDatPhong(MaPhieuDat),
	SoPhong int not null FOREIGN KEY REFERENCES Phong(SoPhong),
	CONSTRAINT PK_ChiTietDatPhong PRIMARY KEY (MaPhieuDat,SoPhong)
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
	NgayTra date not null,
	GioTra datetime not null
)
CREATE TABLE ChiTietHoaDon(
	MaHoaDon nvarchar(20) not null FOREIGN KEY REFERENCES HoaDon(MaHoaDon),
	MaDV nvarchar(20) not null FOREIGN KEY REFERENCES DichVu(MaDV),
	CONSTRAINT PK_ChiTietHoaDon PRIMARY KEY (MaHoaDon,MaDV)
)

INSERT INTO LoaiKhach(MaLoai,TenLoai,KhauTru) 
VALUES (N'V',N'Khách Quen',0.3),
		(N'N',N'Khách Thường',0)

INSERT INTO KhachHang(CCCD, HoTen, SĐT, STK, DiaChi, Email, MaLoaiKH)
VALUES	(N'019283940112', N'Nguyễn Đức Cường', N'0384851290', N'500030001203', N'Q.12, Hồ Chí Minh', N'duccuong16092003@gmail.com', 'V'),
        (N'219233940122', N'Đào Huy Hoàng', N'0183451290', N'533020101203', N'Q.Tân Bình, Hồ Chí Minh', N'hoang11@gmail.com', 'V'),
        (N'362233213322', N'Đặng Nguyễn Minh Thiện', N'0231345129', N'132020101233', N'Q.Gò Vấp, Hồ Chí Minh', N'thienlenin12@gmail.com', 'N'),
        (N'419283940111', N'Lê Thị Thảo', N'0384851291', N'500030001204', N'Q.1, Hồ Chí Minh', N'thaole@gmail.com', 'V'),
        (N'319233940126', N'Nguyễn Hữu Phát', N'0183451291', N'533020101204', N'Q.Bình Thạnh, Hồ Chí Minh', N'phat12@gmail.com', 'V'),
        (N'462233213324', N'Trần Thị Hồng', N'0231345128', N'132020101233', N'Q.Tân Phú, Hồ Chí Minh', N'hongtranh@gmail.com', 'N'),
        (N'539283940124', N'Lê Thanh Tâm', N'0384851292', N'500030001205', N'Q.5, Hồ Chí Minh', N'tamle@gmail.com', 'V'),
        (N'419233940134', N'Phạm Văn Hưng', N'0183451292', N'533020101205', N'Q.7, Hồ Chí Minh', N'hungpham@gmail.com', 'V'),
        (N'562233213326', N'Nguyễn Thị Mai', N'0231345127', N'132020101234', N'Q.9, Hồ Chí Minh', N'mainguyen@gmail.com', 'N'),
        (N'639283940128', N'Hoàng Anh Tuấn', N'0384851293', N'500030001206', N'Q.3, Hồ Chí Minh', N'tuanhoang@gmail.com', 'V'),
        (N'719233940133', N'Đỗ Thị Lan', N'0183451293', N'533020101206', N'Q.8, Hồ Chí Minh', N'lando@gmail.com', 'V'),
        (N'762233213329', N'Phan Văn Đức', N'0231345126', N'132020101235', N'Q.10, Hồ Chí Minh', N'ducphan@gmail.com', 'N'),
        (N'839283940121', N'Trần Thị Tuyết', N'0384851294', N'500030001207', N'Q.11, Hồ Chí Minh', N'tuyettran@gmail.com', 'V'),
        (N'919233940132', N'Vũ Thị Hằng', N'0183451294', N'533020101207', N'Q.2, Hồ Chí Minh', N'hangvu@gmail.com', 'V'),
        (N'962233213331', N'Lê Minh Quân', N'0231345125', N'132020101236', N'Q.4, Hồ Chí Minh', N'quanle@gmail.com', 'N'),
        (N'039283940114', N'Phạm Đức Anh', N'0384851295', N'500030001208', N'Q.6, Hồ Chí Minh', N'anhpham@gmail.com', 'V'),
        (N'119233940131', N'Nguyễn Thị Phương', N'0183451295', N'533020101208', N'Q.Tân Phú, Hồ Chí Minh', N'phuongnguyen@gmail.com', 'V'),
        (N'162233213332', N'Võ Thị Bảo', N'0231345124', N'132020101237', N'Q.Bình Thạnh, Hồ Chí Minh', N'baovo@gmail.com', 'N'),
        (N'239283940110', N'Lê Thị Nhung', N'0384851296', N'500030001209', N'Q.12, Hồ Chí Minh', N'nhungle@gmail.com', 'V'),
        (N'319233940130', N'Đỗ Minh Hòa', N'0183451296', N'533020101209', N'Q.5, Hồ Chí Minh', N'hoamdo@gmail.com', 'V'),
        (N'362233213334', N'Trương Thanh Sơn', N'0231345123', N'132020101238', N'Q.Tân Bình, Hồ Chí Minh', N'sontruong@gmail.com', 'N'),
        (N'439283940118', N'Nguyễn Thị Lan Anh', N'0384851297', N'500030001210', N'Q.Tân Phú, Hồ Chí Minh', N'lananh@gmail.com', 'V'),
        (N'519233940129', N'Vũ Thị Ngọc', N'0183451297', N'533020101210', N'Q.9, Hồ Chí Minh', N'ngocvu@gmail.com', 'V'),
        (N'562233213337', N'Hoàng Minh Phương', N'0231345122', N'132020101239', N'Q.1, Hồ Chí Minh', N'phuonghoang@gmail.com', 'N'),
        (N'639283940116', N'Nguyễn Văn Điền', N'0384851298', N'500030001211', N'Q.2, Hồ Chí Minh', N'dienvan@gmail.com', 'V'),
        (N'719233940127', N'Nguyễn Thị Kim', N'0183451298', N'533020101211', N'Q.6, Hồ Chí Minh', N'kimnguyen@gmail.com', 'V'),
        (N'762233213339', N'Đinh Minh Nhật', N'0231345121', N'132020101240', N'Q.11, Hồ Chí Minh', N'nhatdinh@gmail.com', 'N'),
        (N'839283940113', N'Trần Văn Thắng', N'0384851299', N'500030001212', N'Q.8, Hồ Chí Minh', N'thangtran@gmail.com', 'V'), 
        (N'919283940215', N'Phan Văn Tú', N'0384851296', N'500030001213', N'Q.3, Hồ Chí Minh', N'tuphan@gmail.com', 'V'),
        (N'719233940216', N'Lê Thị Mỹ Linh', N'0183451296', N'533020101213', N'Q.Tân Bình, Hồ Chí Minh', N'mylinh@gmail.com', 'V'),
        (N'562233213440', N'Nguyễn Văn Phát', N'0231345127', N'132020101413', N'Q.Tân Phú, Hồ Chí Minh', N'phatnguyen@gmail.com', 'N'),
        (N'639283940217', N'Trần Thị Lan Anh', N'0384851297', N'500030001214', N'Q.Gò Vấp, Hồ Chí Minh', N'lananhtran@gmail.com', 'V'),
        (N'719233940218', N'Đặng Văn Trí', N'0183451297', N'533020101214', N'Q.Tân Phú, Hồ Chí Minh', N'tuandang@gmail.com', 'V'),
        (N'762233213441', N'Lê Thị Thúy', N'0231345128', N'132020101414', N'Q.Tân Bình, Hồ Chí Minh', N'thuytle@gmail.com', 'N'),
        (N'839283940219', N'Phạm Thị Hà', N'0384851298', N'500030001215', N'Q.5, Hồ Chí Minh', N'hapham@gmail.com', 'V'),
        (N'919233940220', N'Nguyễn Văn Hiếu', N'0183451298', N'533020101215', N'Q.1, Hồ Chí Minh', N'hieunguyen@gmail.com', 'V'),
        (N'962233213442', N'Đỗ Văn Long', N'0231345129', N'132020101415', N'Q.10, Hồ Chí Minh', N'longdo@gmail.com', 'N'),
        (N'039283940221', N'Hoàng Văn Tuấn', N'0384851299', N'500030001216', N'Q.4, Hồ Chí Minh', N'tuanhoang@gmail.com', 'V'),
        (N'119233940222', N'Vũ Thị Hương', N'0183451299', N'533020101216', N'Q.9, Hồ Chí Minh', N'huongvu@gmail.com', 'V'),
        (N'162233213443', N'Phan Văn Đông', N'0231345130', N'132020101416', N'Q.2, Hồ Chí Minh', N'dongphan@gmail.com', 'N'),
        (N'239283940223', N'Trần Thị Bích', N'0384851300', N'500030001217', N'Q.6, Hồ Chí Minh', N'bichtran@gmail.com', 'V'),
        (N'319233940224', N'Nguyễn Minh Hiếu', N'0183451300', N'533020101217', N'Q.8, Hồ Chí Minh', N'hieunguyen2@gmail.com', 'V'),
        (N'362233213444', N'Đinh Thị Hoa', N'0231345131', N'132020101417', N'Q.11, Hồ Chí Minh', N'hoadinh@gmail.com', 'N'),
        (N'439283940225', N'Võ Thị Quỳnh', N'0384851301', N'500030001218', N'Q.Tân Bình, Hồ Chí Minh', N'quynhvo@gmail.com', 'V'),
        (N'519233940226', N'Phạm Văn Điền', N'0183451301', N'533020101218', N'Q.Tân Phú, Hồ Chí Minh', N'dienpham@gmail.com', 'V'),
        (N'562233213445', N'Nguyễn Thị Tâm', N'0231345132', N'132020101418', N'Q.Gò Vấp, Hồ Chí Minh', N'tamnguyen@gmail.com', 'N'),
        (N'639283940227', N'Vũ Minh Phúc', N'0384851302', N'500030001219', N'Q.5, Hồ Chí Minh', N'phucvu@gmail.com', 'V'),
        (N'719233940228', N'Trần Văn Toàn', N'0183451302', N'533020101219', N'Q.7, Hồ Chí Minh', N'toantran@gmail.com', 'V'),
        (N'762233213446', N'Nguyễn Văn Đại', N'0231345133', N'132020101419', N'Q.12, Hồ Chí Minh', N'dainguyen@gmail.com', 'N'),
        (N'839283940229', N'Hoàng Thị Ngọc', N'0384851303', N'500030001220', N'Q.3, Hồ Chí Minh', N'ngochuong@gmail.com', 'V');

INSERT INTO NhanVien(MaNV,TenNV,SĐT,DiaChi,Email)
VALUES	('NV001', N'Nguyễn Văn An',N'0127894391',N'Q.Gò Vấp','vanan1111@gmail.com'),
		('NV002', N'Nguyễn Văn Linh',N'0235824391',N'Q.Gò Vấp','vanminh1214@gmail.com'),
		('NV003', N'Nguyễn Thị Thảo', N'0367843121', N'Q.1', 'thaonguyen@gmail.com'),
		('NV004', N'Phạm Văn Tú', N'0287645123', N'Q.Tân Bình', 'tupham@gmail.com'),
		('NV005', N'Trần Thị Hà', N'0341234098', N'Q.10', 'hatran@gmail.com'),
		('NV006', N'Vũ Minh Anh', N'0398745123', N'Q.3', 'anhvu@gmail.com'),
		('NV007', N'Hoàng Thị Hoa', N'0239857432', N'Q.5', 'hoahong@gmail.com'),
		('NV008', N'Lê Văn Tuấn', N'0323489456', N'Q.Tân Phú', 'tuanle@gmail.com'),
		('NV009', N'Nguyễn Thị Lan', N'0367859345', N'Q.7', 'lannguyen@gmail.com'),
		('NV010', N'Trương Văn Định', N'0287345124', N'Q.12', 'dinhtruong@gmail.com');


INSERT INTO LoaiPhong(MaLoai,TenLoai,Gia)
VALUES (N'STD',N'Phòng Standard',1500000),
		(N'SUP',N'Phòng Superior',3000000);		

INSERT INTO Phong(SoPhong,TenPhong,LoaiPhong,IsEmpty)
VALUES  (01,'T1.01','STD',1),
        (02,'T1.02','STD',1),
        (03,'T1.03','STD',1),
        (04,'T1.04','STD',1),
        (05,'T1.05','STD',1),
        (06,'T1.06','STD',1),
        (07,'T1.07','STD',1),
        (08,'T1.08','STD',1),
        (09,'T1.09','STD',1),
        (10,'T1.10','STD',1),
        (11,'T2.11','STD',1),
        (12,'T2.12','STD',1),
        (13,'T2.13','STD',1),
        (14,'T2.14','STD',1),
        (15,'T2.15','STD',1),
        (16,'T2.16','STD',1),
        (17,'T2.17','STD',1),
        (18,'T2.18','STD',1),
        (19,'T2.19','STD',1),
        (20,'T2.20','STD',1),
        (21,'T3.21','STD',1),
        (22,'T3.22','STD',1),
        (23,'T3.23','STD',1),
        (24,'T3.24','STD',1),
        (25,'T3.25','STD',1),
        (26,'T3.26','STD',1),
        (27,'T3.27','STD',1),
        (28,'T3.28','STD',1),
        (29,'T3.29','STD',1),
        (30,'T3.30','STD',1),
        (31,'T4.31','SUP',1),
        (32,'T4.32','SUP',1),
        (33,'T4.33','SUP',1),
        (34,'T4.34','SUP',1),
        (35,'T4.35','SUP',1),
        (36,'T4.36','SUP',1),
        (37,'T4.37','SUP',1),
        (38,'T4.38','SUP',1),
        (39,'T4.39','SUP',1),
        (40,'T5.40','SUP',1);

--INSERT INTO PhieuDatPhong(MaPhieuDat, MaNV, CCCD, NgayDen, NgayDi,soLuong)
--VALUES	
--		('PD054', 'NV001', N'019283940112', '2023-05-11', '2023-05-15',4),
--		('PD055', 'NV001', N'019283940112', '2023-05-11', '2023-05-15',4),
--		('PD056', 'NV001', N'019283940112', '2023-05-11', '2023-05-15',4),
--		('PD053', 'NV008', N'919283940215', '2023-07-20', '2023-07-22',1),
--		('PD052', 'NV008', N'919283940215', '2023-07-20', '2023-07-22',1),
--		('PD051', 'NV001', N'019283940112', '2023-05-11', '2023-05-15',1),
--		('PD001', 'NV001', N'019283940112', '2023-05-11', '2023-05-15',4),
--		('PD002', 'NV002', N'219233940122', '2023-05-12', '2023-05-14',1),
--		('PD003', 'NV001', N'362233213322', '2023-05-04', '2023-05-09',1),
--		('PD004', 'NV002', N'039283940114', '2023-06-01', '2023-06-05',1),
--		('PD005', 'NV003', N'039283940221', '2023-06-02', '2023-06-04',1),
--		('PD006', 'NV004', N'119233940131', '2023-06-03', '2023-06-09',1),
--		('PD007', 'NV005', N'119233940222', '2023-06-04', '2023-06-08',1),
--		('PD008', 'NV006', N'162233213332', '2023-06-05', '2023-06-07',1),
--		('PD009', 'NV007', N'162233213443', '2023-06-06', '2023-06-10',1),
--		('PD010', 'NV008', N'219233940122', '2023-06-07', '2023-06-09',1),
--		('PD011', 'NV009', N'239283940110', '2023-06-08', '2023-06-12',1),
--		('PD012', 'NV010', N'239283940223', '2023-06-09', '2023-06-11',1),
--		('PD013', 'NV001', N'319233940126', '2023-06-10', '2023-06-14',1),
--		('PD014', 'NV002', N'319233940130', '2023-06-11', '2023-06-13',1),
--		('PD015', 'NV003', N'319233940224', '2023-06-12', '2023-06-16',1),
--		('PD016', 'NV004', N'362233213322', '2023-06-13', '2023-06-15',1),
--		('PD017', 'NV005', N'362233213334', '2023-06-14', '2023-06-18',1),
--		('PD018', 'NV006', N'362233213444', '2023-06-15', '2023-06-17',1),
--		('PD019', 'NV007', N'419233940134', '2023-06-16', '2023-06-20',1),
--		('PD020', 'NV008', N'419283940111', '2023-06-17', '2023-06-19',1),
--		('PD021', 'NV009', N'439283940118', '2023-06-18', '2023-06-22',1),
--		('PD022', 'NV010', N'439283940225', '2023-06-19', '2023-06-21',1),
--		('PD023', 'NV001', N'462233213324', '2023-06-20', '2023-06-24',1),
--		('PD024', 'NV002', N'519233940129', '2023-06-21', '2023-06-23',1),
--		('PD025', 'NV003', N'519233940226', '2023-06-22', '2023-06-26',1),
--		('PD026', 'NV004', N'539283940124', '2023-06-23', '2023-06-25',1),
--		('PD027', 'NV005', N'562233213326', '2023-06-24', '2023-06-28',1),
--		('PD028', 'NV006', N'562233213337', '2023-06-25', '2023-06-27',1),
--		('PD029', 'NV007', N'562233213440', '2023-06-26', '2023-06-30',1),
--		('PD030', 'NV008', N'562233213445', '2023-06-27', '2023-06-29',1),
--		('PD031', 'NV009', N'639283940116', '2023-06-28', '2023-07-02',1),
--		('PD032', 'NV010', N'639283940128', '2023-06-29', '2023-07-01',1),
--		('PD033', 'NV001', N'639283940217', '2023-07-03', '2023-07-07',1),
--		('PD034', 'NV002', N'639283940227', '2023-07-04', '2023-07-06',1),
--		('PD035', 'NV003', N'719233940127', '2023-07-05', '2023-07-09',1),
--		('PD036', 'NV004', N'719233940133', '2023-07-06', '2023-07-08',1),
--		('PD037', 'NV005', N'719233940216', '2023-07-07', '2023-07-11',1),
--		('PD038', 'NV006', N'719233940218', '2023-07-08', '2023-07-10',1),
--		('PD039', 'NV007', N'719233940228', '2023-07-09', '2023-07-13',1),
--		('PD040', 'NV008', N'762233213329', '2023-07-10', '2023-07-12',1),
--		('PD041', 'NV009', N'762233213339', '2023-07-11', '2023-07-15',1),
--		('PD042', 'NV010', N'762233213441', '2023-07-12', '2023-07-14',1),
--		('PD043', 'NV001', N'762233213446', '2023-07-13', '2023-07-17',1),
--		('PD044', 'NV002', N'839283940113', '2023-07-14', '2023-07-16',1),
--		('PD045', 'NV003', N'839283940121', '2023-07-15', '2023-07-19',1),
--		('PD046', 'NV004', N'839283940219', '2023-07-16', '2023-07-18',1),
--		('PD047', 'NV005', N'839283940229', '2023-07-17', '2023-07-21',1),
--		('PD048', 'NV006', N'919233940132', '2023-07-18', '2023-07-20',1),
--		('PD049', 'NV007', N'919233940220', '2023-07-19', '2023-07-23',1),
--		('PD050', 'NV008', N'919283940215', '2023-07-20', '2023-07-22',1);
--INSERT INTO ChiTietDatPhong (MaPhieuDat, SoPhong)
--VALUES	
--		('PD0055',05),
--		('PD0056',07),
--		('PD0055',02),
--		('PD0054',08),
--		('PD053',09),
--		('PD052',03),
--		('PD0051',01),
--		('PD001', 05),
--		('PD001', 04),
--		('PD001', 03),
--		('PD001', 01),
--		('PD002', 02),
--		('PD003', 03),
--		('PD004', 04),
--		('PD005', 05),
--		('PD006', 06),
--		('PD007', 07),
--		('PD008', 08),
--		('PD009', 09),
--		('PD010', 10),
--		('PD011', 11),
--		('PD012', 12),
--		('PD013', 13),
--		('PD014', 14),
--		('PD015', 15),
--		('PD016', 16),
--		('PD017', 17),
--		('PD018', 18),
--		('PD019', 19),
--		('PD020', 20),
--		('PD021', 21),
--		('PD022', 22),
--		('PD023', 23),
--		('PD024', 24),
--		('PD025', 25),
--		('PD026', 26),
--		('PD027', 27),
--		('PD028', 28),
--		('PD029', 29),
--		('PD030', 30),
--		('PD031', 31),
--		('PD032', 32),
--		('PD033', 33),
--		('PD034', 34),
--		('PD035', 35),
--		('PD036', 36),
--		('PD037', 37),
--		('PD038', 38),
--		('PD039', 39),
--		('PD040', 40),
--		('PD041', 01),  
--		('PD042', 02),
--		('PD043', 03),
--		('PD044', 04),
--		('PD045', 05),
--		('PD046', 06),
--		('PD047', 07),
--		('PD048', 08),
--		('PD049', 09),
--		('PD050', 10);

--INSERT INTO PhieuNhanPhong (MaPhieuNhan, MaPhieuDat, GioNhan, NgayNhan)
--VALUES
--		('PN001', 'PD001', '2023-05-12 10:34:09 AM', '2023-05-12'),
--		('PN002', 'PD002', '2023-05-13 05:34:19 AM', '2023-05-13'),
--		('PN003', 'PD003', '2023-05-05 09:44:19 PM', '2023-05-05'),
--		('PN004', 'PD004', '2023-06-01 10:34:09 AM', '2023-06-01'),
--		('PN005', 'PD005', '2023-06-02 05:34:19 AM', '2023-06-02'),
--		('PN006', 'PD006', '2023-06-03 09:44:19 PM', '2023-06-03'),
--		('PN007', 'PD007', '2023-06-04 10:34:09 AM', '2023-06-04'),
--		('PN008', 'PD008', '2023-06-05 05:34:19 AM', '2023-06-05'),
--		('PN009', 'PD009', '2023-06-06 09:44:19 PM', '2023-06-06'),
--		('PN010', 'PD010', '2023-06-07 10:34:09 AM', '2023-06-07'),
--		('PN011', 'PD011', '2023-06-08 05:34:19 AM', '2023-06-08'),
--		('PN012', 'PD012', '2023-06-09 09:44:19 PM', '2023-06-09'),
--		('PN013', 'PD013', '2023-06-10 10:34:09 AM', '2023-06-10'),
--		('PN014', 'PD014', '2023-06-11 05:34:19 AM', '2023-06-11'),
--		('PN015', 'PD015', '2023-06-12 09:44:19 PM', '2023-06-12'),
--		('PN016', 'PD016', '2023-06-13 10:34:09 AM', '2023-06-13'),
--		('PN017', 'PD017', '2023-06-14 05:34:19 AM', '2023-06-14'),
--		('PN018', 'PD018', '2023-06-15 09:44:19 PM', '2023-06-15'),
--		('PN019', 'PD019', '2023-06-16 10:34:09 AM', '2023-06-16'),
--		('PN020', 'PD020', '2023-06-17 05:34:19 AM', '2023-06-17'),
--		('PN021', 'PD021', '2023-06-18 09:44:19 PM', '2023-06-18'),
--		('PN022', 'PD022', '2023-06-19 10:34:09 AM', '2023-06-19'),
--		('PN023', 'PD023', '2023-06-20 05:34:19 AM', '2023-06-20'),
--		('PN024', 'PD024', '2023-06-21 09:44:19 PM', '2023-06-21'),
--		('PN025', 'PD025', '2023-06-22 10:34:09 AM', '2023-06-22'),
--		('PN026', 'PD026', '2023-06-23 05:34:19 AM', '2023-06-23'),
--		('PN027', 'PD027', '2023-06-24 09:44:19 PM', '2023-06-24'),
--		('PN028', 'PD028', '2023-06-25 10:34:09 AM', '2023-06-25'),
--		('PN029', 'PD029', '2023-06-26 05:34:19 AM', '2023-06-26'),
--		('PN030', 'PD030', '2023-06-27 09:44:19 PM', '2023-06-27'),
--		('PN031', 'PD031', '2023-06-28 10:34:09 AM', '2023-06-28'),
--		('PN032', 'PD032', '2023-06-29 05:34:19 AM', '2023-06-29'),
--		('PN033', 'PD033', '2023-07-03 10:34:09 AM', '2023-07-03'),
--		('PN034', 'PD034', '2023-07-04 05:34:19 AM', '2023-07-04'),
--		('PN035', 'PD035', '2023-07-05 09:44:19 PM', '2023-07-05'),
--		('PN036', 'PD036', '2023-07-06 10:34:09 AM', '2023-07-06'),
--		('PN037', 'PD037', '2023-07-07 05:34:19 AM', '2023-07-07'),
--		('PN038', 'PD038', '2023-07-08 09:44:19 PM', '2023-07-08'),
--		('PN039', 'PD039', '2023-07-09 10:34:09 AM', '2023-07-09'),
--		('PN040', 'PD040', '2023-07-10 05:34:19 AM', '2023-07-10'),
--		('PN041', 'PD041', '2023-07-11 09:44:19 PM', '2023-07-11'),
--		('PN042', 'PD042', '2023-07-12 10:34:09 AM', '2023-07-12'),
--		('PN043', 'PD043', '2023-07-13 05:34:19 AM', '2023-07-13'),
--		('PN044', 'PD044', '2023-07-14 09:44:19 PM', '2023-07-14'),
--		('PN045', 'PD045', '2023-07-15 10:34:09 AM', '2023-07-15'),
--		('PN046', 'PD046', '2023-07-16 05:34:19 AM', '2023-07-16'),
--		('PN047', 'PD047', '2023-07-17 09:44:19 PM', '2023-07-17'),
--		('PN048', 'PD048', '2023-07-18 10:34:09 AM', '2023-07-18'),
--		('PN049', 'PD049', '2023-07-19 05:34:19 AM', '2023-07-19'),
--		('PN050', 'PD050', '2023-07-20 09:44:19 PM', '2023-07-20');


INSERT INTO DichVu(MaDV,TenDV,GiaDV)
VALUES	('DV101', N'Dịch vụ Karaoke'  , 300000),
		('DV102', N'Dịch vụ Trông Trẻ', 150000),
		('DV103', N'Dịch vụ Thuê Xe'  , 400000),
		('DV104', N'Dịch vụ Massage'  , 250000),
		('DV105', N'Dịch vụ Làm Đẹp'  , 350000),
		('DV106', N'Dịch vụ Phòng Họp', 500000);

--INSERT INTO HoaDon(MaHoaDon,MaPhieu,NgayTra,GioTra)
--VALUES	
--		('HD001','PN001', '2023-05-15 09:34:09 AM', '2023-05-15'),
--		('HD002','PN002', '2023-05-14 11:25:09 AM', '2023-05-14'),
--		('HD003','PN003', '2023-05-09 02:43:09 PM', '2023-05-09'),
--		('HD004','PN004', '2023-06-05 08:15:09 AM', '2023-06-05'),
--		('HD005','PN005', '2023-06-04 04:50:09 PM', '2023-06-04'),
--		('HD006','PN006', '2023-06-09 01:30:09 PM', '2023-06-09'),
--		('HD007','PN007', '2023-06-08 05:12:09 PM', '2023-06-08'),
--		('HD008','PN008', '2023-06-07 03:28:09 PM', '2023-06-07'),
--		('HD009','PN009', '2023-06-10 06:40:09 AM', '2023-06-10'),
--		('HD010','PN010', '2023-06-09 10:20:09 AM', '2023-06-09'),
--		('HD011','PN011', '2023-06-12 01:55:09 PM', '2023-06-12'),
--		('HD012','PN012', '2023-06-11 06:02:09 AM', '2023-06-11'),
--		('HD013','PN013', '2023-06-14 07:45:09 PM', '2023-06-14'),
--		('HD014','PN014', '2023-06-13 09:34:09 AM', '2023-06-13'),
--		('HD015','PN015', '2023-06-16 03:15:09 PM', '2023-06-16'),
--		('HD016','PN016', '2023-06-15 11:28:09 AM', '2023-06-15')



INSERT INTO ChiTietHoaDon (MaHoaDon, MaDV)
VALUES	('HD012', 'DV002'),
		('HD001', 'DV002'),
		('HD001', 'DV003'),
		('HD001', 'DV004'),
		('HD001', 'DV001'),
		('HD002', 'DV001'),
		('HD003', 'DV002'),
		('HD004', 'DV003'),
		('HD005', 'DV004'),
		('HD006', 'DV005'),
		('HD007', 'DV006'),
		('HD008', 'DV001'),
		('HD009', 'DV002'),
		('HD010', 'DV003'),
		('HD011', 'DV004'),
		('HD012', 'DV005'),
		('HD013', 'DV006'),
		('HD014', 'DV001'),
		('HD015', 'DV002'),
		('HD016', 'DV003')



select MaPhieuNhan, PhieuDatPhong.MaPhieuDat,GioNhan,NgayNhan from PhieuNhanPhong join PhieuDatPhong on PhieuDatPhong.MaPhieuDat = PhieuNhanPhong.MaPhieuDat


select * from HoaDon join ChiTietHoaDon

-- Hàm lấy danh sách phiếu phòng bằng mã phiếu
select ChiTietDatPhong.SoPhong , TenPhong, LoaiPhong, IsEmpty
from PhieuDatPhong join ChiTietDatPhong 
	on PhieuDatPhong.MaPhieuDat = ChiTietDatPhong.MaPhieuDat join Phong 
		on ChiTietDatPhong.SoPhong = Phong.SoPhong
where PhieuDatPhong.MaPhieuDat = 'PD001'

EXECUTE getListPhongByMaPhieuDat @maPhieuDat = 'PD001'

--Hàm lấy phòng chưa nhận

EXECUTE getListPhongChuaNhan

--Hàm Lấy phòng đag đặt chưa trả


select * from PhieuDatPhong
where CCCD = '719233940127' 


select ChiTietDatPhong.MaPhieuDat from Phong join ChiTietDatPhong 
	on Phong.SoPhong = ChiTietDatPhong.SoPhong JOIN PhieuDatPhong 
		ON PhieuDatPhong.MaPhieuDat = ChiTietDatPhong.MaPhieuDat join PhieuNhanPhong
			on PhieuNhanPhong.MaPhieuDat = PhieuDatPhong.MaPhieuDat 
where MaPhieuNhan not in (select MaPhieu from HoaDon) AND IsEmpty = 0

EXECUTE getListPhongChuaTra


EXECUTE getListTrangThaiPhong @trangThai = 0


-- Ham insert PhieuDatPhong

('PD0058', 'NV001', N'019283940112', '2023-05-11', '2023-05-15',1)
EXECUTE insertPhieuDat @maPhieuDat = 'PD0058', @maNV  = 'NV001' , @CCCD =  N'019283940112' , @soLuong = 1, @ngayDen = '2023-05-11' , @ngayDi = '2023-05-18'

--Ham insert ChiTietDatPhong
EXECUTE insertChiTietDatPhong @maPhieuDat = 'PD0058', @soPhong = 04

-- Ham updatePhieuDat

select * from PhieuDatPhong
where MaPhieuDat = 'PD001'

select * from Phong 


select DichVu.MaDV, TenDV, GiaDV
					from HoaDon join ChiTietHoaDon
					on HoaDon.MaHoaDon = ChiTietHoaDon.MaHoaDon join DichVu 
					on DichVu.MaDV = ChiTietHoaDon.MaDV
					where HoaDon.MaHoaDon = 'HD012'

--Ham insertPhieuNhan
--EXECUTE insertPhieuNhan @maPhieuNhan nvarchar(20) ,@maPhieuDat nvarchar(20) ,	@gioNhan datetime ,	@ngayNhan datetime 


--Ham update Phieu Nhan
--EXECUTE updatePhieuNhan @maPhieuNhan nvarchar(20) ,@maPhieuDat nvarchar(20) ,	@gioNhan datetime ,	@ngayNhan datetime 