package com.example.haily.learnuikongjian;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by haily on 2016/11/23.
 */

public class MySQLitedatabase extends SQLiteOpenHelper {
    Context context;

    public MySQLitedatabase(Context context) {
        super(context, "users", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
              db.execSQL("  CREATE TABLE  IF  NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT ,name TEXT ,pass TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
