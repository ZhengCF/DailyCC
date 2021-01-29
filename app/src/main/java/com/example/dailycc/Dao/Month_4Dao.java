package com.example.dailycc.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dailycc.Class.Month_4;
import com.example.dailycc.Helper.MyHelper_month_4;

import java.util.ArrayList;
import java.util.List;

public class Month_4Dao {
    private MyHelper_month_4 myHelper_month_4;

    public Month_4Dao(Context context) {
        myHelper_month_4 = new MyHelper_month_4(context);
    }

    public void insert(Month_4 month_4) {
        //获取数据库对象
        SQLiteDatabase db = myHelper_month_4.getWritableDatabase();
        //
        ContentValues values = new ContentValues();
        values.put("date", month_4.getDate());
        values.put("zhifubao", month_4.getZhiFuBao());
        values.put("jingdong", month_4.getJingDong());
        values.put("meituan", month_4.getMeiTuan());
        values.put("weixin", month_4.getWeiXin());
        values.put("yunshanfu",month_4.getYunShanFu());
        values.put("sum",month_4.getSum());
        //insert values to details
        long id = db.insert("month_4s", null, values);
        month_4.setId(id);
        db.close();
    }

    //update
    public void update(Month_4 month_4) {
        SQLiteDatabase db = myHelper_month_4.getWritableDatabase();
        ContentValues values = new ContentValues();    //data need to change
        System.out.println(month_4);
        values.put("zhifubao", month_4.getZhiFuBao());
        values.put("jingdong", month_4.getJingDong());
        values.put("meituan", month_4.getMeiTuan());
        values.put("weixin", month_4.getWeiXin());
        values.put("yunshanfu",month_4.getYunShanFu());
        values.put("sum",month_4.getSum());
        int id = db.update("month_4s", values, "date=?", new String[]{month_4.getDate() + ""});
        //update and return number of row
        db.close();
    }

    public List<Month_4> queryAll() {
        SQLiteDatabase db = myHelper_month_4.getWritableDatabase();
        List<Month_4> list = new ArrayList<>();
        Cursor c = db.query("month_4s", null, null, null, null, null, "_id DESC");
        while (c.moveToNext()) {
            //
            long id = c.getLong(c.getColumnIndex("_id"));
            int date=c.getInt(c.getColumnIndex("date"));
            double zhifubao = c.getDouble(c.getColumnIndex("zhifubao"));
            double jingdong = c.getDouble(c.getColumnIndex("jingdong"));
            double meituan = c.getDouble(c.getColumnIndex("meituan"));
            double weixin = c.getDouble(c.getColumnIndex("weixin"));
            double yunshanfu=c.getDouble(c.getColumnIndex("yunshanfu"));
            double sum=c.getInt(c.getColumnIndex("sum"));
            list.add(new Month_4(id, date, zhifubao, jingdong, meituan, weixin, yunshanfu, sum));
        }
        c.close();
        db.close();
        return list;
    }
}
