package com.zyh.dbcat.test;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ruoyun on 2017/8/22.
 */

public interface IDataSupport<T> {

    /**
     * 插入对象
     *
     * @param t
     * @return
     */
    public int insert(T t);

    void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz);
}
