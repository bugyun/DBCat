package com.zyh.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zyh.dbcat.test.DaoSupportFactory;
import com.zyh.dbcat.test.IDataSupport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void test() {
        DaoSupportFactory factory = DaoSupportFactory.getFactory();
        IDataSupport<People> dao = factory.getDao(People.class);
        dao.insert(new People());


    }
}
