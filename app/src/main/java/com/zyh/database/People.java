package com.zyh.database;

import com.zyh.dbcat.annotate.Column;
import com.zyh.dbcat.annotate.DBVersion;

/**
 * Created by ruoyun on 16/8/31.
 */
@DBVersion(version = 1)
public class People {

    @Column(version = 1)
    private int age;

    @Column(version = 2)
    private String name;


    public void createSQL() {

    }
}
