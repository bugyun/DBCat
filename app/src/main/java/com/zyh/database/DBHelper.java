package com.zyh.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ruoyun on 16/8/31.
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * @param context 上下文
     * @param name    数据库名字
     * @param factory
     * @param version 版本号
     */
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.getVersion();//获取数据库的

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
