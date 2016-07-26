package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mobilesafe.R;

public class SplashActivity extends Activity {

    private TextView tv_version_name;
    private int mLocalVersionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除当前activity头title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        //初始化UI
        initUI();
        //初始化数据
        initData();
    }

    /**
     * 获取数据方法
     */
    private void initData() {
        //1.应用版本名称
        tv_version_name.setText("版本名称:" + getVersionName());
        //检测(本地版本号和服务器版本号比对)是否有更新,如果有更新,提示用户下载
        //2.获取本地版本号
        mLocalVersionCode = getVersionCode();
        //3.获取服务器版本号(客户端发请求,服务端给响应,(json,xml))
        //http://www.oxxx.com/update74.json?key=value 返回200请求成功,流的方式将数据读取下来
        //json中内容包含
        /**
         * 更新版本的版本名称
         * 新版本的描述信息
         * 服务器版本号
         * 新版本apk下载地址
         */
    }

    /**
     * 初始化UI方法
     */
    private void initUI() {
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
    }

    /**
     * 获取版本名称:清单文件中
     * @return 应用版本名称 返回null代表异常
     */
    public String getVersionName() {
        //1.包管理者对象packageManager
        PackageManager pm = getPackageManager();
        //2.从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        try {
            PackageInfo packageInfo =  pm.getPackageInfo(getPackageName(), 0);
            //3.获取版本名称
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反悔版本号
     * @return 非0则代表获取成功
     */
    public int getVersionCode() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo =  pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
