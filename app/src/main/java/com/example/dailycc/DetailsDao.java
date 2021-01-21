package com.example.dailycc;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DetailsDao {
    private MyHelper helper;

    public DetailsDao(Context context) {
        helper = new MyHelper(context);

    }

    public void insert(Details details) {
        //获取数据库对象
        SQLiteDatabase db = helper.getWritableDatabase();
        //
        ContentValues values = new ContentValues();

        values.put("type", details.isType());
        values.put("date", details.getDate());
        values.put("count", details.getCount());
        values.put("note", details.getNote());
        //insert values to details
        long id = db.insert("details", null, values);
        details.setId(id);
        db.close();
    }

    //delete data by id
    public int delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.delete("details", "_id=?", new String[]{id + ""});
        //delete and return the number of row
        db.close();
        return count;
    }

    //update
    public int update(Details details) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();    //data need to change

        values.put("type", details.isType());
        values.put("date", details.getDate());
        values.put("count", details.getCount());
        values.put("note", details.getNote());
        int count = db.update("details", values, "_id=?", new String[]{details.getId() + ""});
        //update and return number of row
        db.close();
        return count;

    }

    //查询指定id数据
    public Details select(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from details where _id=?", new String[]{id + ""});

//		long _id=c.getLong(c.getColumnIndex("_id"));
        c.moveToNext();
        String note = c.getString(c.getColumnIndex("note"));
        String date = c.getString(c.getColumnIndex("date"));
        double count = c.getDouble(c.getColumnIndex("count"));
        Integer type = c.getInt(c.getColumnIndex("type"));
        Details a = new Details(id, type, count, date, note);
        db.close();
        return a;
    }

    //查询所有数据正序排列
    public List<Details> queryAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<Details> list = new ArrayList<Details>();
        Cursor c = db.query("details", null, null, null, null, null, "date DESC");
        while (c.moveToNext()) {
            //
            long id = c.getLong(c.getColumnIndex("_id"));
            String note = c.getString(4);
            String date = c.getString(3);
            double count = c.getDouble(2);
            Integer type = c.getInt(1);
            list.add(new Details(id, type, count, date, note));
        }
        c.close();
        db.close();
        return list;
    }

    //删除所有数据
    public List<Details> deleteAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<Details> list = new ArrayList<Details>();
        Cursor c = db.query("details", null, null, null, null, null, "_id DESC");
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
        Cursor c = db.query("details", null, null, null, null, null, "_id DESC");
        while (c.moveToNext()) {
            //long id=c.getLong(c.getColumnIndex("_id"));
            Integer type = c.getInt(1);
            double count = c.getDouble(2);
            if (type.equals(0)) {
                totals[0] += count;
            } else {
                totals[1] += count;
            }
        }
        return totals;
    }

    //查询某一周的记录
    //查询某个月的记录
    public int[] sltWeeks() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query("details", null, null, null, null, null, "date DESC");
        int[] weeks = {};
        while (c.moveToNext()) {
            int week = c.getInt(c.getColumnIndex("date"));
            double count = c.getDouble(c.getColumnIndex("count"));
            weeks[week/100] += count;
        }
        return weeks;
    }

    //查询某一年的记录
    public int[] sltYears() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query("details", null, null, null, null, null, "date DESC");
        int[] years = {};
        while (c.moveToNext()) {
            int year = c.getInt(c.getColumnIndex("date"));
            double count = c.getDouble(c.getColumnIndex("count"));
            years[year/10000] += count;
        }
        return years;
    }

    //查询任一时段的记录
    public int sltT2T(String dateSt, String dateSp) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from details where date>? and date<?", new String[]{dateSt, dateSp});
        int ttSum = 0;
//		long _id=c.getLong(c.getColumnIndex("_id"));
        while (c.moveToNext()) {

            double count = c.getDouble(c.getColumnIndex("count"));
            ttSum += count;
        }
        db.close();
        return ttSum;
    }

}
