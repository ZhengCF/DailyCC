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

        values.put("zhifubao", month_4.getZhiFuBao());
        values.put("jingdong", month_4.getJingDong());
        values.put("weixin", month_4.getWeiXin());
        values.put("meituan", month_4.getMeiTuan());
        //insert values to details
        long id = db.insert("month_4s", null, values);
        month_4.setId(id);
        db.close();
    }

    //update
    public void update(Month_4 month_4) {
        SQLiteDatabase db = myHelper_month_4.getWritableDatabase();
        ContentValues values = new ContentValues();    //data need to change

        values.put("zhifubao", month_4.getZhiFuBao());
        values.put("jingdong", month_4.getJingDong());
        values.put("weixin", month_4.getWeiXin());
        values.put("meituan", month_4.getMeiTuan());
        int id = db.update("month_4s", values, "_id=?", new String[]{month_4.getId() + ""});
        //update and return number of row
        db.close();
    }

    public List<Month_4> queryAll() {
        SQLiteDatabase db = myHelper_month_4.getWritableDatabase();
        List<Month_4> list = new ArrayList<Month_4>();
        Cursor c = db.query("month_4s", null, null, null, null, null, "_id DESC");
        while (c.moveToNext()) {
            //
            long id = c.getLong(c.getColumnIndex("_id"));
            double zhifubao = c.getDouble(4);
            double jingdong = c.getDouble(3);
            double weixin = c.getDouble(2);
            double meituan = c.getDouble(1);
            list.add(new Month_4(id, zhifubao, jingdong, weixin, meituan));
        }
        c.close();
        db.close();
        return list;
    }
}
