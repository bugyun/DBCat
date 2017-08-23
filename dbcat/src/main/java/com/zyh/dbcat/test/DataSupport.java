package com.zyh.dbcat.test;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by ruoyun on 2017/8/22.
 */

public class DataSupport<T> implements IDataSupport<T> {

    private static final String TAG = DataSupport.class.getSimpleName();
    private SQLiteDatabase sqLiteDatabase;    //外部数据库的引用
    private Class<T> clazz;
    public static final String CREATE_TABLE = "create table Person (" + "id integer primary key autoincrement, " + "name text, " + "age integer, " + "flag boolean)";


    @Override
    public void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.clazz = clazz;
        //创建表
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists ")//
                .append(DaoUtil.getTableName(clazz))//
                .append(" (id integer primary key autoincrement, ");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            //通过反射来获得clazz的属性
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getType().getSimpleName();//基本数据类型
            //type需要进行转换 int--->integer,String -->text
            sb.append(name).append(DaoUtil.getColumnType(type)).append(" ,");
        }
        sb.replace(sb.length() - 2, sb.length(), ")");
        String createTableSql = sb.toString();
        Log.i(TAG, "表语句-->" + createTableSql);
        sqLiteDatabase.execSQL(createTableSql);
    }

    @Override
    public long insert(T t) {
        //通过反射来获取
        ContentValues values = new ContentValues();

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            //通过反射来获得clazz的属性
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getType().getSimpleName();//基本数据类型
            //type需要进行转换 int--->integer,String -->text

        }

        values.put("", "");
        //null  速度比第三方快一倍左右
        return sqLiteDatabase.insert(DaoUtil.getTableName(clazz), null, values);
    }

    //


    //


    //

}

