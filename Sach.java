package com.example.quanlythuvien.dto;

public class Sach {
//    them thuoc tinh hinh anh vao day
    public int maSach;
    public String tenSach;
    public String giaMua;
    public String moTa;
    public String soLuong;
    public int maLoai;
    public byte[] hinh;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, String giaMua, String moTa, String soLuong, int maLoai, byte[] hinh) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaMua = giaMua;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
        this.hinh = hinh;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(String giaMua) {
        this.giaMua = giaMua;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}