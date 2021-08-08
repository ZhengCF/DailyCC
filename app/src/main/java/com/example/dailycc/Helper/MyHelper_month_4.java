package com.example.dailycc.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper_month_4 extends SQLiteOpenHelper {
    public MyHelper_month_4(Context context) {
        super(context, "month_4.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        System.out.println("onCreat");
        db.execSQL("CREATE TABLE month_4s(_id INTEGER PRIMARY KEY AUTOINCREMENT,date CHAR(20),zhifubao CHAR(20),jingdong CHAR(20),weixin CHAR(20),meituan CHAR(20),yunshanfu CHAR(20), sum CHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        System.out.println("onUpgrade");
    }

}
