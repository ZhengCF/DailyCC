package com.example.dailycc.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper_account_details extends SQLiteOpenHelper{
	public MyHelper_account_details(Context context){
		super(context,"account.db",null,2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("onCreat");
		db.execSQL("CREATE TABLE account_details(_id INTEGER PRIMARY KEY AUTOINCREMENT,type INTEGER,note VARCHAR(20),value CHAR(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("onUpgrade");
	}

}
