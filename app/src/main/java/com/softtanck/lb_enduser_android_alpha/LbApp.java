package com.softtanck.lb_enduser_android_alpha;

import android.app.Application;

/**
 * Created by winterfell on 12/3/2015.
 */
public class LbApp extends Application {

    private static LbApp instance;

    public ActivityManager manager;


    public static LbApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        manager = ActivityManager.getManager();
    }
}
