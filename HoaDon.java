package com.example.quanlythuvien.dto;

public class HoaDon {
    public int maHD;
    public int maNV;
    public int maKH;
    public int maSach;
    public String ngay;
    public String giaHD;
    public int trangThai;

    public HoaDon() {
    }

    public HoaDon(int maHD, int maNV, int maKH, int maSach, String ngay, String giaHD, int trangThai) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maSach = maSach;
        this.ngay = ngay;
        this.giaHD = giaHD;
        this.trangThai = trangThai;
    }
}