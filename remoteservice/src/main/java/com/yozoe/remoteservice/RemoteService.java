package com.yozoe.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yozoe.itheima.Iservice;

/**
 * Created by wangdong on 16/7/24.
 *
 * 远程服务
 */
public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("hehe", "服务已启动");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("hehe", "服务已关闭");
    }

    public String testMethod() {
//        Log.d("hehe", "我是远程服务里的方法");

        return "cacade";

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    //1定义中间人对象
    private class MyBinder extends Iservice.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String callTestMethod() {
            return testMethod();
        }
    }
}
