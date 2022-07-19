package com.example.quanlythuvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlythuvien.database.DbHelper;
import com.example.quanlythuvien.dto.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach ob){
        ContentValues values = new ContentValues();
        values.put("tenLoai", ob.tenLoai);
        return db.insert("LoaiSach",null,values);
    }

    public int update(LoaiSach ob){
        ContentValues values = new ContentValues();
        values.put("tenLoai",ob.tenLoai);
        return db.update("LoaiSach",values,"maLoai=?", new String[]{String.valueOf(ob.maLoai)});
    }

    public int delete(String id){
        return db.delete("LoaiSach","maLoai=?", new String[]{id});
    }

    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        LoaiSach objLoaiSach = new LoaiSach();
        String[] args = new String[]{id + ""};
        Cursor c = db.rawQuery("SELECT maLoai, tenLoai FROM LoaiSach WHERE maLoai=?", args);
        if(c.moveToFirst()){
            objLoaiSach.maLoai = c.getInt(0);
            objLoaiSach.tenLoai = c.getString(1);
        }
        return objLoaiSach;
    }

   private List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<LoaiSach>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
           LoaiSach ob = new LoaiSach();
           ob.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
           ob.tenLoai = c.getString(c.getColumnIndex("tenLoai"));
           list.add(ob);
       }
       return list;
   }

   public List<LoaiSach> getSearch_loaiSach(String tenLoai){
        String sql = "SELECT * FROM LoaiSach WHERE tenLoai LIKE '%"+tenLoai+"%' ";
        return getData(sql);
   }

}
