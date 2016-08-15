package com.yozoe.myworld;

import android.app.Application;

import com.yozoe.common.BaseApplication;

/**
 * Created by wangdong on 16/8/8.
 */
public class MyWorldApplication extends BaseApplication {

    private static MyWorldApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static BaseApplication getContext() {
        return mApplication;
    }
}
