package com.example.quanlythuvien.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.fragment.TopFragment;
import com.example.quanlythuvien.dto.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment fragment;
    private ArrayList<Top> lists;
    TextView tvTenSach, tvSL;

    public TopAdapter(@NonNull Context context, TopFragment fragment, ArrayList<Top> lists) {
        super(context, 0,lists);
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
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item = lists.get(position);
        if(item != null){
            tvTenSach = v.findViewById(R.id.tvTenSachT);
            tvTenSach.setText("Tên Sách : "+item.tenSach);
            tvSL = v.findViewById(R.id.tvsoLuong);
            tvSL.setText("Số Lượng: "+item.slTOP);
            tvTenSach.setTextColor(Color.GRAY);
            tvSL.setTextColor(Color.GRAY);
        }
        return v;
    }

}
