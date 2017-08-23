package com.zyh.dbcat.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by ruoyun on 2017/8/22.
 * 第三方：1，方便 2，不想折腾 3，心里总认为第三方的好
 */
public class DataSupport<T> implements IDataSupport<T> {

    private static final String TAG = DataSupport.class.getSimpleName();
    private SQLiteDatabase sqLiteDatabase;    //外部数据库的引用
    private Class<T> clazz;
    public static final String CREATE_TABLE = "create table Person (" + "id integer primary key autoincrement, " + "name text, " + "age integer, " + "flag boolean)";
    private final Object[] mPutMethodArgs = new Object[2];
    private static final Map<String, Method> mPutMethodMap = new ArrayMap<>();

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
        ContentValues values = ContentValuesByObj(t);
        //null  速度比第三方快一倍左右
        return sqLiteDatabase.insert(DaoUtil.getTableName(clazz), null, values);
    }

    @Override
    public boolean insert(List<T> list) {
        sqLiteDatabase.beginTransaction();
        for (T item : list) {
            insert(item);
        }
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        return false;
    }

    @Override
    public List<T> queryAll() {
        Cursor cursor = sqLiteDatabase.query(DaoUtil.getTableName(clazz), null, null, null, null, null, null);
        return cursorToList();
    }

    private List<T> cursorToList() {


        return null;
    }

    @Override
    public List<T> query(String sql) {
        return null;
    }

    public int update(T obj, String where, String... whereAgrs) {
        ContentValues values = ContentValuesByObj(obj);
        return sqLiteDatabase.update(DaoUtil.getTableName(clazz), values, where, whereAgrs);
    }


    //删除
    public int delete(String where, String... whereAgrs) {
        return sqLiteDatabase.delete(DaoUtil.getTableName(clazz), where, whereAgrs);
    }


    //obj 转成 contentvalues
    private ContentValues ContentValuesByObj(T t) {
        //使用第三方 使用比对一下 了解下源码
        ContentValues values = new ContentValues();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            try {
                //通过反射来获得clazz的属性
                field.setAccessible(true);
                String name = field.getName();
                Object o = field.get(t);
                //可以观看google的源码AppCompatViewInflater,

                String filedTypeName = field.getType().getName();
                Method putMethod = mPutMethodMap.get(filedTypeName);

                if (putMethod == null) {
                    putMethod = ContentValues.class.getDeclaredMethod("put", String.class, o.getClass());
                    mPutMethodMap.put(filedTypeName, putMethod);
                }
                //
                mPutMethodArgs[0] = name;
                mPutMethodArgs[1] = o;
                putMethod.invoke(values, mPutMethodArgs);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mPutMethodArgs[0] = null;
                mPutMethodArgs[1] = null;
            }
            //            String type = field.getType().getSimpleName();//基本数据类型
            //type需要进行转换 int--->integer,String -->text
        }
        //        values.put("", "");
        return values;
    }


    //


    //


    //

}

