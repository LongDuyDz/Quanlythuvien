package com.example.quanlythuvien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.dao.SachDAO;
import com.example.quanlythuvien.dao.HoaDonDAO;
import com.example.quanlythuvien.dao.KhachHangDAO;
import com.example.quanlythuvien.fragment.HoaDonFragment;
import com.example.quanlythuvien.dto.Sach;
import com.example.quanlythuvien.dto.HoaDon;
import com.example.quanlythuvien.dto.KhachHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {
    private Context context;
    private HoaDonFragment fragment;
    private ArrayList<HoaDon> lists;
    TextView tvMaHD, tvHDTenKH, tvHDTenSach, tvHDGiaMua, tvHDNgayMua, tvHDTrangThai;
    ImageView imgDeleteHD;
    SachDAO sachDAO;
    HoaDonDAO hoaDonDAO;
    KhachHangDAO khachHangDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonAdapter(@NonNull Context context, HoaDonFragment fragment, ArrayList<HoaDon> lists) {
        super(context, 0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.hoa_don_item,null);
        }
        final HoaDon item = lists.get(position);
        if(item != null){
            tvMaHD = v.findViewById(R.id.tvMaHD_item);
            tvMaHD.setText("ID: "+item.maHD);

            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.maSach));
            tvHDTenSach = v.findViewById(R.id.tvTenSach_HD_item);
            tvHDTenSach.setText("Sách: "+sach.tenSach);

            khachHangDAO = new KhachHangDAO(context);
            KhachHang khachHang = khachHangDAO.getID(String.valueOf(item.maKH));
            tvHDTenKH = v.findViewById(R.id.tvTenKH_HD_item);
            tvHDTenKH.setText("Khách hàng: "+khachHang.tenKH);

            tvHDGiaMua = v.findViewById(R.id.tvGiaMua_HD_item);
            tvHDGiaMua.setText("Giá: "+item.giaHD);

            tvHDNgayMua = v.findViewById(R.id.tvNgayMua_HD_item);
            tvHDNgayMua.setText("Date: "+item.ngay);

            tvHDTrangThai = v.findViewById(R.id.tvTrangThai_HD_item);
            if(item.trangThai == 1){
                tvHDTrangThai.setTextColor(Color.GREEN);
                tvHDTrangThai.setText("Đã thanh toán");
            }else{
                tvHDTrangThai.setTextColor(Color.RED);
                tvHDTrangThai.setText("Chưa thanh toán");
            }
            imgDeleteHD = v.findViewById(R.id.img_delete_HD);
        }
        imgDeleteHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoaHoaDon(String.valueOf(item.maHD));
            }
        });
        return v;
    }
}
