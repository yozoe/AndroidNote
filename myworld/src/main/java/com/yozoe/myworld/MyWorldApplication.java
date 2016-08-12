package com.yozoe.myworld;

import android.app.Application;

/**
 * Created by wangdong on 16/8/8.
 */
public class MyWorldApplication extends Application {

    private static MyWorldApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
    }

    public static MyWorldApplication getContext() {
        return mApplication;
    }
}
