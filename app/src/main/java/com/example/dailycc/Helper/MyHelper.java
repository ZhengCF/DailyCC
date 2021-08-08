package com.example.dailycc.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper{
	public MyHelper(Context context){
		super(context,"com.db",null,2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("onCreat");
		db.execSQL("CREATE TABLE details(_id INTEGER PRIMARY KEY AUTOINCREMENT,type INTEGER,count CHAR(20),date VARCHAR(20),note VARCHAR(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("onUpgrade");
	}

}
