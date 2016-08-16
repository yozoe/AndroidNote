package com.yozoe.common;

import android.app.Application;

/**
 * Created by wangdong on 16/3/10.
 */
public class BaseApplication extends Application {

    private static BaseApplication mApplication;

    public static BaseApplication getContext() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
