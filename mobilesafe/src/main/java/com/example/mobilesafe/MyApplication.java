package com.example.mobilesafe;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wangdong on 16/8/2.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
