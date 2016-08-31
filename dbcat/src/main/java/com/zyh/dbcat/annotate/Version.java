package com.zyh.dbcat.annotate;

/**
 * Created by ruoyun on 16/8/31.
 */
public @interface Version {
    int version() default 1;
}
