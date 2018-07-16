package com.zyh.dbcat.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ruoyun on 16/8/31.
 * 数据库版本version
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface DBVersion {
    int version() default 1;
}
