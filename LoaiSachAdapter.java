package com.example.quanlythuvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.fragment.LoaiSachFragment;
import com.example.quanlythuvien.dto.LoaiSach;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tv_maLoai, tv_tenLoai;
    ImageView img_delete;
    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> lists){
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
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item,null);
        }

        final LoaiSach item = lists.get(position);
        if(item != null){
            tv_maLoai = v.findViewById(R.id.tv_maLoai_item);
            tv_maLoai.setText("Mã Loại: "+item.maLoai);
            tv_tenLoai = v.findViewById(R.id.tv_tenLoai_item);
            tv_tenLoai.setText("Tên Loại: "+item.tenLoai);
            img_delete = v.findViewById(R.id.img_delete_LS_item);
        }
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoaLoaiSach(String.valueOf(item.maLoai));
            }
        });
        return v;

    }
}
