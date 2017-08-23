package com.zyh.dbcat.test;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by ruoyun on 2017/8/22.
 */

public interface IDataSupport<T> {

    void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz);

    /**
     * 插入对象
     *
     * @param t
     * @return
     */
    public long insert(T t);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    public boolean insert(List<T> list);

    public List<T> queryAll();

    public List<T> query(String sql);

}
