package com.zyh.dbcat.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ruoyun on 16/8/31.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE = "create table Person ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "age integer, "
            + "flag boolean)";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库
        createSql();
        db.execSQL(CREATE_TABLE);

    }

    private void createSql() {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
            case 2:
            case 3:
                break;
        }
    }




}
