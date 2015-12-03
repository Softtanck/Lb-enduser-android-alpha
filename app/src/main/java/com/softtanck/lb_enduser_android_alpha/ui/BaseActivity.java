package com.softtanck.lb_enduser_android_alpha.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.softtanck.lb_enduser_android_alpha.ActivityManager;
import com.softtanck.lb_enduser_android_alpha.LbApp;

/**
 * Created by winterfell on 11/27/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected LbApp app;

    protected Context context;

    protected ActivityManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        setContentView(getLayoutId());

        app = LbApp.getInstance();
        manager = app.manager;

        context = this;
        manager.addAc(this);

        onCreate();
    }

    @Override
    protected void onDestroy() {
        manager.rmAc(this);
        super.onDestroy();
    }


    /**
     * 更改视图
     *
     * @param clazz
     */
    public void changeAc(Class clazz) {
        Intent intent = new Intent(context, clazz);
        startActivity(intent);
    }

    /**
     * 改变视图 [带参数]
     *
     * @param clazz
     * @param bundle 参数
     * @param key    参数key
     */
    public void changeAc(Class clazz, Bundle bundle, String key) {
        if (null != bundle) {
            Intent intent = new Intent(context, clazz);
            intent.putExtra(key, bundle);
            startActivity(intent);
        }
    }


    /**
     * 获取布局ID
     *
     * @return
     */
    public abstract int getLayoutId();


    /**
     * 当视图被创建
     */
    public abstract void onCreate();

}
