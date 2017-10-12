package com.zyh.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zyh.dbcat.test.DaoSupportFactory;
import com.zyh.dbcat.test.IDataSupport;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test() {
        //1为什么用factory 目前的数据实在内存卡中的 有时候我们需要放到data/data/xxxx/database
        //获取的factory不一样，那么写入的位置 是可以不一样的

        //2.面向接口编程，获取IDaoSupport 那么不需要关心实现，目前的实现是我们自己写的，方便以后使用第三方的

        //3.为了高扩展
        DaoSupportFactory factory = DaoSupportFactory.getFactory();
        IDataSupport<People> dao = factory.getDao(People.class);
        dao.insert(new People());
    }

    public void testLoad() {

        Schedulers.newThread();
        Schedulers.io();
        Schedulers.computation();
        Schedulers.single();
        Schedulers.trampoline();



    }

}
