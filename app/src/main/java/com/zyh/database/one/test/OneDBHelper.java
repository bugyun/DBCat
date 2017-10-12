package com.zyh.database.one.test;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zyh.database.People;

/**
 * Created by ruoyun on 2017/10/12.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */

public class OneDBHelper extends SQLiteOpenHelper {

    public OneDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public OneDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableHelper.getCreateTableSql(People.class));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                //如果老版本是1，则创建X表
            case 2:
                //如果老版本是2，则修改表X的结构(例如添加两个字段)
            default:
                break;
        }
    }
}
