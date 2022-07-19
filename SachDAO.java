package com.example.quanlythuvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlythuvien.database.DbHelper;
import com.example.quanlythuvien.dto.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Sach ob){
        ContentValues values = new ContentValues();
        values.put("tenSach",ob.tenSach);
        values.put("giaMua",ob.giaMua);
        values.put("moTa",ob.moTa);
        values.put("soLuong",ob.soLuong);
        values.put("maLoai", ob.maLoai);
        values.put("hinh", ob.hinh);
        return db.insert("Sach",null,values);
    }
    public int update(Sach ob){
        ContentValues values = new ContentValues();
        values.put("tenSach",ob.tenSach);
        values.put("giaMua",ob.giaMua);
        values.put("moTa",ob.moTa);
        values.put("soLuong",ob.soLuong);
        values.put("maLoai", ob.maLoai);
        values.put("hinh", ob.hinh);
        return db.update("Sach",values,"maSach=?", new String[]{String.valueOf(ob.maSach)});
    }
    public int delete(String id){
        return db.delete("Sach","maSach=?", new String[]{id});
    }

    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    public Sach getID(String id){
        Sach objSach = new Sach();
        String[] argss = new String[]{ id + ""};
    Cursor c = db.rawQuery("SELECT maSach, tenSach, giaMua, moTa, soLuong, maLoai, hinh FROM Sach WHERE maSach=?", argss);
    if(c.moveToFirst()){
        objSach.maSach = c.getInt(0);
        objSach.tenSach = c.getString(1);
        objSach.giaMua = c.getString(2);
        objSach.moTa = c.getString(3);
        objSach.soLuong = c.getString(4);
        objSach.maLoai = c.getInt(5);
        objSach.hinh = c.getBlob(6);
    }
    return objSach;
}

//Get data nhieu tham so
   private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<Sach>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
          Sach ob = new Sach();
           ob.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
           ob.tenSach = c.getString(c.getColumnIndex("tenSach"));
           ob.giaMua = c.getString(c.getColumnIndex("giaMua"));
           ob.moTa = c.getString(c.getColumnIndex("moTa"));
           ob.soLuong = c.getString(c.getColumnIndex("soLuong"));
           ob.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
           ob.hinh = c.getBlob(c.getColumnIndex("hinh"));
           list.add(ob);
       }
       return list;
   }

   public List<Sach> getSearch_Sach(String tenSach){
        String sql = "SELECT * FROM Sach WHERE tenSach LIKE '%"+tenSach+"%' ";
        return getData(sql);
    }
}
