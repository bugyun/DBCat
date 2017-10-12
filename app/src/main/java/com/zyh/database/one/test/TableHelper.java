package com.zyh.database.one.test;

import android.util.Log;

import com.zyh.database.one.DBUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static android.content.ContentValues.TAG;

/**
 * Created by ruoyun on 2017/10/12.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:创建数据库表的帮助类
 */

public class TableHelper {

    public static final String CREATE_TABLE = "create table Person ("//
            + "id integer primary key autoincrement, "//
            + "name text, "//
            + "age integer, "//
            + "flag boolean)";

    private static String createSQL(Class clazz) {
        //获取注解
        Annotation[] annotations = clazz.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Annotation annotation = annotations[i];
        }
        //获取属性变量
        Field[] method = clazz.getFields();
        for (int i = 0; i < method.length; i++) {
            Field field = method[i];
            //使用了method.setAccessible(true)后 性能有了20倍的提升，将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查
            field.setAccessible(true);
        }
        return CREATE_TABLE;
    }


    /**
     * 得到建表语句
     *
     * @param clazz 指定类
     * @return sql语句
     */
    public static String getCreateTableSql(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        //将类名作为表名
        String tabName = DBUtils.getTableName(clazz);
        sb.append("create table ").append(tabName).append(" (id  INTEGER PRIMARY KEY AUTOINCREMENT, ");
        //得到类中所有属性对象数组
        Field[] fields = clazz.getDeclaredFields();
        for (Field fd : fields) {
            String fieldName = fd.getName();
            String fieldType = fd.getType().getName();
            if (fieldName.equalsIgnoreCase("_id") || fieldName.equalsIgnoreCase("id")) {
                continue;
            } else {
                sb.append(fieldName).append(DBUtils.getColumnType(fieldType)).append(", ");
            }
        }
        int len = sb.length();
        sb.replace(len - 2, len, ")");
        Log.d(TAG, "the result is " + sb.toString());
        return sb.toString();
    }


}
