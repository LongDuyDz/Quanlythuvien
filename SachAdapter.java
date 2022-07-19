package com.example.quanlythuvien.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.dao.LoaiSachDAO;
import com.example.quanlythuvien.fragment.SachFragment;
import com.example.quanlythuvien.dto.Sach;
import com.example.quanlythuvien.dto.LoaiSach;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {

    private Context context;
    SachFragment fragment;
    private ArrayList<Sach> lists;
    TextView tvMaSach, tvTenSach, tvGiaMua, tvMaLoaiSach, tvMoTa, tvSoLuong;
    ImageView imgDeleteSach, imgHinh;

    public SachAdapter(@NonNull Context context, SachFragment fragment, ArrayList<Sach> lists){
        super(context,0,lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item = lists.get(position);
        if(item != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.maLoai));

            tvMaSach = v.findViewById(R.id.tv_maSach_item);
            tvMaSach.setText("Mã Sách: "+item.maSach);

            tvTenSach = v.findViewById(R.id.tv_tenSach_item);
            tvTenSach.setText("Tên Sách: "+item.tenSach);

            tvSoLuong = v.findViewById(R.id.tv_soLuongG_item);
            tvSoLuong.setText("SoLượng: "+item.soLuong);

            tvGiaMua = v.findViewById(R.id.tv_giaMua_item);
            tvGiaMua.setText("Giá Mua: "+item.giaMua+" VNĐ");

            tvMaLoaiSach = v.findViewById(R.id.tv_maLoaiSach_item);
            tvMaLoaiSach.setText("Loại Sách: "+loaiSach.tenLoai);

            tvMoTa = v.findViewById(R.id.tv_moTa_item);
            tvMoTa.setText("Mô tả: "+item.moTa);

            imgHinh = v.findViewById(R.id.img_sach_item);
            byte[] hinhSach = item.hinh;
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhSach, 0, hinhSach.length);
            imgHinh.setImageBitmap(bitmap);

            imgDeleteSach = v.findViewById(R.id.img_delete_sach_item);
        }

        imgDeleteSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.xoaSach(String.valueOf(item.maSach));
            }
        });


        return v;

    }

}
