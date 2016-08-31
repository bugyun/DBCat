package com.zyh.dbcat.annotate;

/**
 * Created by ruoyun on 16/8/31.
 * 数据库版本version
 */
public @interface DBVersion {
    int version() default 1;
}
