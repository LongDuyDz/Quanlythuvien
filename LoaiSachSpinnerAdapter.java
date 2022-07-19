package com.example.quanlythuvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.dto.LoaiSach;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoaiSachSP, tvTenLoaiSachSP;

    public LoaiSachSpinnerAdapter(@NonNull Context context , ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spinner,null);
        }
        final LoaiSach item = lists.get(position);
        if(item != null){
            tvMaLoaiSachSP = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSachSP.setText(item.maLoai+". ");
            tvTenLoaiSachSP = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSachSP.setText(item.tenLoai);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spinner,null);
        }
        final LoaiSach item = lists.get(position);
        if(item != null){
            tvMaLoaiSachSP = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSachSP.setText(item.maLoai+". ");
            tvTenLoaiSachSP = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSachSP.setText(item.tenLoai);
        }
        return v;
    }
}
