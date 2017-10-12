package com.zyh.database.one;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

/**
 * Created by ruoyun on 2017/10/12.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */

public class UseHelper {
    private static final String DB_NAME = "demo.db";
    private static final int DB_VERSION = 1;
    private DBManager dbManager;
    private int index = 0;


    public void begin(Context context) {
        dbManager = new DBManager(context, DB_NAME, DB_VERSION, Person.class);
    }

    public void close() {
        dbManager.closeDataBase();
    }

    public void insert() {
        index++;
        Person person = new Person("name" + index, 2, true);
        dbManager.insert(person);
    }

    public void update() {
        ContentValues values = new ContentValues();
        values.put("age", 34);
        dbManager.updateById(Person.class, values, 1);
    }

    public void delete() {
        int id = index--;
        dbManager.deleteById(Person.class, id);
    }

    public void findAll() {
        List<Person> list = dbManager.findAll(Person.class);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = list.size(); i < size; i++) {
            sb.append(list.get(i).toString()).append("\n");
        }
    }

    public void findRecord() {
        Person p = dbManager.findById(Person.class, 1);
    }

    public static void main(Context context) {
        UseHelper useHelper = new UseHelper();
        useHelper.begin(context);
        useHelper.insert();
        useHelper.update();
        useHelper.delete();
        useHelper.findAll();
        useHelper.findRecord();
        useHelper.close();
    }


}
