create database DuAn1
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
);

create table Kho(
	MaKho varchar(10) not null primary key,
	DiaChi nvarchar(30),
	TenKho nvarchar(30),
	MaQL varchar(10),
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
);

create table NhanVien(
	MaNV varchar(10) not null primary key,
	TenNv nvarchar(20),
	GioiTinh nvarchar(5),
	NgaySinh date,
	DiaChi nvarchar(50),
	Sdt nvarchar(10),
	MaQL varchar(10),
);

create table QuanLy(
	TaiKhoan nvarchar(max),
	MatKhau nvarchar(max),
	MaQL varchar(10),
);
