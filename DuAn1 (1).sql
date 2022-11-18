create database DuAn1
go
use DuAn1
go
create table Bia(
	MaBia varchar(10) not null primary key,
	Ten nvarchar(20),
	GiaNhap float,
	GiaBan float,
	NSX date,
	HSD date,
	Mota nvarchar(30),
	MaNSX varchar(10) not null,
);

create table NhaSx(
	MaNSX varchar(10) not null primary key,
	TenNSX nvarchar(30),
);

create table Kho(
	MaKho varchar(10) not null primary key,
	DiaChi nvarchar(30),
	TenKho nvarchar(30),
);

create table KhoChiTiet(
	MaBia varchar(10) not null,
	MaKho varchar(10) not null,
	SoLuong int,
);

create table HoaDon(
	MaHD varchar(10) not null primary key,
	MaNV varchar(10) not null,
	NgayTao date,
);

create table HoaDonChiTIet(
	MaHD varchar(10) not null,
	MaBia varchar(10) not null,
	SoLuong int,
	DonGia float,
);

create table NhanVien(
	MaNV varchar(10) not null primary key,
	TenNv nvarchar(20),
	GioiTinh nvarchar(5),
	NgaySinh date,
	DiaChi nvarchar(50),
	Sdt nvarchar(10),
	MaQL varchar(10) not null,
);

create table TaiKhoan(
	ChucVu varchar(10) not null,
	TaiKhoan nvarchar(max),
	MatKhau nvarchar(max),
);

--Tạo khóa chính bảng Hóa đơn chi tiết
alter table HoaDonChiTiet add constraint PK_HoaDonChiTiet
primary key(MaHD,MaBia)



--Tạo quan hệ giữa các bảng
--Bia - NhaSx
alter table Bia add foreign key (MaNsx) references NhaSx(MaNsx)
--Bia - Kho
--alter table Bia add foreign key (MaKho) references Kho(MaKho)
--Kho - QuanLy
--alter table Kho add foreign key (MaQL) references QuanLy(MaQL)
--NhanVien - QuanLy
--alter table NhanVien add foreign key (MaQL) references QuanLy(MaQL)
--HoaDon - NhanVien 
alter table HoaDon add foreign key (MaNV) references NhanVien(MaNV)
--HoaDonChiTiet - HoaDon - Bia
alter table HoaDonChiTiet add foreign key (MaHD) references HoaDon(MaHD)
alter table HoaDonChiTiet add foreign key (MaBia) references Bia(MaBia)

--------------------------------------------

alter table KhoChiTiet add constraint Pk_KhoChiTiet primary key(MaBia,MaKho)
alter table KhoChiTiet add foreign key (MaKho) references Kho(MaKho)

alter table KhoChiTiet add foreign key (MaBia) references Bia(MaBia)


insert into Kho values ('K1','Ha Noi','Kho Bia 01')

insert into Bia values ('SP1','Bia Ha Noi',10000,15000,'10-1-2022','01-1-2024','Bia truyen thong Ha Noi','NSX1'),
					   ('SP2','Bia Sai Gon',12000,20000,'10-1-2022','01-1-2024','Bia Sai Gon Special','NSX2');

insert into NhaSx values ('NSX1','Thang Long'),
						 ('NSX2','Sai Gon'),
						 ('NSX3','Nghe An');

insert into KhoChiTiet values('SP1','K1',500),
							 ('SP2','K1',200);
						
select A.TenKho,C.Ten,A.DiaChi,B.SoLuong from Kho A join KhoChiTiet B on A.MaKho = B.MaKho join Bia C on B.MaBia = C.MaBia

insert into Bia values ('SP3','Bia Tiger',10000,15000,'10-1-2022','01-1-2024','Bruh','NSX3')

insert into NhaSx (MaNSX,TenNSX) values ('HNK','Heniken')
insert into Bia (MaBia,Ten,GiaNhap,GiaBan,NSX,HSD,MaNSX) values ('HNK001','BiaHeniken',10000,20000,'2022-11-18','2023-11-18','HNK')

insert into KhoChiTiet values('KNH001','K1',140)