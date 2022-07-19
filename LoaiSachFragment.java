package com.example.quanlythuvien.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.adapter.LoaiSachAdapter;
import com.example.quanlythuvien.dao.LoaiSachDAO;
import com.example.quanlythuvien.dto.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class LoaiSachFragment extends Fragment {

    ListView lv;
    ArrayList<LoaiSach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaiSach, edTenLoaiSach, ed_search;
    Button btnSaveLS, btnCancelLS;
    ImageView img_search;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lv = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fab_LS);
        ed_search = v.findViewById(R.id.ed_search_loaiSach);
        img_search = v.findViewById(R.id.img_search_loaiSach);
        dao = new LoaiSachDAO(getActivity());
        capNhatLv();

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_search.getText().length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin trước khi Search", Toast.LENGTH_SHORT).show();
                }
                capNhatTen_loaiSach();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);  // 1 update
                return false;
            }
        });

        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaLoaiSach = dialog.findViewById(R.id.ed_maLoai_dialog);
        edTenLoaiSach = dialog.findViewById(R.id.edtenLoai_dialog);
        btnCancelLS = dialog.findViewById(R.id.btnCancelLS);
        btnSaveLS = dialog.findViewById(R.id.btnSaveLS);
        edMaLoaiSach.setEnabled(false);
        if(type != 0){
            edMaLoaiSach.setText(String.valueOf(item.maLoai));
            edTenLoaiSach.setText(item.tenLoai);
        }

        btnCancelLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSach();
                item.tenLoai = edTenLoaiSach.getText().toString();
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm Loại Sách Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.maLoai = Integer.parseInt(edMaLoaiSach.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Cập nhật Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Cập nhật Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoaLoaiSach(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá mục này không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                Snackbar.make(getView(),"Bạn đã xóa thành công",Snackbar.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLv(){
        list = (ArrayList<LoaiSach>)dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    void capNhatTen_loaiSach(){
        list = (ArrayList<LoaiSach>)dao.getSearch_loaiSach(ed_search.getText().toString());
        adapter = new LoaiSachAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    public int validate(){
        int check = 1;
        if(edTenLoaiSach.getText().length()==0){
            Toast.makeText(getContext(),"Bạn chưa nhập Tên Loại Sách", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}