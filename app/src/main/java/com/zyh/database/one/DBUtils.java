package com.zyh.database.one;

import android.text.TextUtils;

import java.util.Locale;

/**
 * Created by ruoyun on 2017/10/12.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */

public class DBUtils {

    //得到每一列字段的数据类型
    public static String getColumnType(String type) {
        String value = null;
        if (type.contains("String")) {
            value = " text ";
        } else if (type.contains("int")) {
            value = " integer ";
        } else if (type.contains("boolean")) {
            value = " boolean ";
        } else if (type.contains("float")) {
            value = " float ";
        } else if (type.contains("double")) {
            value = " double ";
        } else if (type.contains("char")) {
            value = " varchar ";
        } else if (type.contains("long")) {
            value = " long ";
        }
        return value;
    }

    //得到表名
    public static String getTableName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    //大写
    public static String capitalize(String string) {
        if (!TextUtils.isEmpty(string)) {
            return string.substring(0, 1).toUpperCase(Locale.US) + string.substring(1);
        }
        return string == null ? null : "";
    }
}
