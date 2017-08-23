package com.zyh.dbcat.test;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ruoyun on 2017/8/22.
 */

public class DataSupport<T> implements IDataSupport<T> {

    private SQLiteDatabase sqLiteDatabase;    //外部数据库的引用
    private Class<T> clazz;

    @Override
    public void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.clazz = clazz;
        //创建表

        //通过反射来获得clazz的属性


        sqLiteDatabase.execSQL("");
    }

    @Override
    public int insert(T t) {

        return 0;
    }
}

