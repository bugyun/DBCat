package com.zyh.dbcat.test;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Created by ruoyun on 2017/8/22.
 */

public class DaoSupportFactory {

    private static DaoSupportFactory daoSupportFactory = new DaoSupportFactory();
    private final SQLiteDatabase sqLiteDatabase;    //外部数据库的引用

    public static DaoSupportFactory getFactory() {
        return daoSupportFactory;
    }

    private DaoSupportFactory() {
        //判断是否有存储卡
        //判断6.0系统权限，动态申请权限
        File dbRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test" + File.separator + "database");
        if (!dbRoot.exists()) {
            boolean mkdirs = dbRoot.mkdirs();
        }

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "test.db");

        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);

    }


    public <T> IDataSupport<T> getDao(Class<T> clazz) {
        IDataSupport iDataSupport = new DataSupport();
        iDataSupport.init(sqLiteDatabase, clazz);
        return iDataSupport;
    }


}
