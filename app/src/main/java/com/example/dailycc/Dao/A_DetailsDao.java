package com.example.dailycc.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dailycc.Class.A_Details;
import com.example.dailycc.Helper.MyHelper_account_details;

import java.util.ArrayList;
import java.util.List;

public class A_DetailsDao {
    private MyHelper_account_details helper;

    public A_DetailsDao(Context context) {
        helper = new MyHelper_account_details(context);

    }

    public void insert(A_Details a_details) {
        //获取数据库对象
        SQLiteDatabase db = helper.getWritableDatabase();
        //
        ContentValues values = new ContentValues();

        values.put("type", a_details.isType());
        values.put("note", a_details.getNote());
        values.put("value", a_details.getValue());
        //insert values to details
        long id = db.insert("account_details", null, values);
        a_details.setId(id);
        db.close();
    }

    //delete data by id
    public int delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.delete("account_details", "_id=?", new String[]{id + ""});
        //delete and return the number of row
        db.close();
        return count;
    }

    //update
    public int update(A_Details a_details) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();    //data need to change

        values.put("type", a_details.isType());
        values.put("value", a_details.getValue());
        values.put("note", a_details.getNote());
        int count = db.update("account_details", values, "note=? and type=?", new String[]{a_details.getNote() + "", a_details.isType() + ""});
        //update and return number of row
        db.close();
        return count;

    }

    //查询指定id数据
    public boolean isExist(String note, int type) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from account_details where note=? and type=?", new String[]{note + "", type + ""});

//		long _id=c.getLong(c.getColumnIndex("_id"));
        c.moveToNext();
        if (c.getCount() > 0) {
            c.close();
            db.close();
            return true;
        } else {
            c.close();
            db.close();
            return false;
        }
//        return a;
    }

    //查询指定note数据
    public A_Details select(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from account_details where _id=?", new String[]{id + ""});

//		long _id=c.getLong(c.getColumnIndex("_id"));
        c.moveToNext();
        String note = c.getString(c.getColumnIndex("note"));
        double value = c.getDouble(c.getColumnIndex("value"));
        int type = c.getInt(c.getColumnIndex("type"));
        A_Details a = new A_Details(id, type, note, value);
        c.close();
        db.close();
        return a;
    }

    //查询所有数据正序排列
    public List<A_Details> queryAll_n() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<A_Details> list = new ArrayList<A_Details>();
        Cursor c = db.query("account_details", new String[]{"_id, type, note, value"}, "type=?", new String[]{"0"}, null, null, "_id DESC");
        while (c.moveToNext()) {
            //
            long id = c.getLong(c.getColumnIndex("_id"));
            String note = c.getString(c.getColumnIndex("note"));
            double value = c.getDouble(c.getColumnIndex("value"));
            int type = c.getInt(c.getColumnIndex("type"));
            list.add(new A_Details(id, type, note, value));
        }
        c.close();
        db.close();
        return list;
    }

    //查询所有数据正序排列
    public List<A_Details> queryAll_p() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<A_Details> list = new ArrayList<A_Details>();
        Cursor c = db.query("account_details", new String[]{"_id, type, note, value"}, "type=?", new String[]{"1"}, null, null, "_id DESC");
        while (c.moveToNext()) {
            //
            long id = c.getLong(c.getColumnIndex("_id"));
            String note = c.getString(c.getColumnIndex("note"));
            double value = c.getDouble(c.getColumnIndex("value"));
            int type = c.getInt(c.getColumnIndex("type"));
            list.add(new A_Details(id, type, note, value));
        }
        c.close();
        db.close();
        return list;
    }

    //删除所有数据
    public List<A_Details> deleteAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<A_Details> list = new ArrayList<A_Details>();
        Cursor c = db.query("account_details", null, null, null, null, null, "_id DESC");
        while (c.moveToNext()) {
            //
            long id = c.getLong(c.getColumnIndex("_id"));
            delete(id);
        }
        c.close();
        db.close();
        return list;
    }

    //查询各类的总和
    public double[] total() {
        SQLiteDatabase db = helper.getWritableDatabase();
        double[] totals = new double[2];
        Cursor c = db.query("account_details", null, null, null, null, null, "_id DESC");
        while (c.moveToNext()) {
            //long id=c.getLong(c.getColumnIndex("_id"));
            Integer type = c.getInt(c.getColumnIndex("type"));
            double value = c.getDouble(c.getColumnIndex("value"));
            if (type.equals(0)) {
                totals[0] += value;
            } else {
                totals[1] += value;
            }
        }
        return totals;
    }

}
