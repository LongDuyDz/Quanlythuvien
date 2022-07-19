package com.example.quanlythuvien.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlythuvien.database.DbHelper;
import com.example.quanlythuvien.dto.Sach;
import com.example.quanlythuvien.dto.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

//    thong ke TOP5
    public List<Top> getTop(){
        String sqlTop = "SELECT maSach, count(maSach) as slTOP FROM HoaDon GROUP BY maSach ORDER BY slTOP DESC LIMIT 5";
        List<Top> list = new ArrayList<Top>();
        SachDAO SachDAO = new SachDAO(context);
        Cursor c = db.rawQuery(sqlTop, null);

        while (c.moveToNext()){
            Top top = new Top();
            Sach sach = SachDAO.getID(c.getString(c.getColumnIndex("maSach")));
            top.tenSach = sach.tenSach;
            top.slTOP = Integer.parseInt(c.getString(c.getColumnIndex("slTOP")));
            list.add(top);
        }
      return list;
    }

    public int getDanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(giaHD) as doanhThu FROM HoaDon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay,denNgay});

        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        c.close();
        return list.get(0);
    }
}
