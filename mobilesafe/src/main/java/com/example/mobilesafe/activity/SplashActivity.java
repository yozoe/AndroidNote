package com.example.mobilesafe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesafe.R;
import com.example.mobilesafe.util.StreamUtil;
import com.example.mobilesafe.util.ToastUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

public class SplashActivity extends Activity {

    /**
     * 更新系版本的状态码
     */
    private static final int UPDATE_VERSION = 100;

    /**
     * 进入应用程序主界面的状态码
     */
    private static final int ENTER_HOME = 101;

    /**
     * url地址出错
     */
    private static final int URL_ERROR = 102;
    private static final int IO_ERROR = 103;
    private static final int JSON_ERROR = 104;
    private TextView tv_version_name;
    private int mLocalVersionCode;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VERSION:
                    //弹出对话框提示用户更新
                    showUpdateDialog();
                    break;
                case ENTER_HOME:
                    //进入用用主界面,activity跳转过程
                    enterHome();
                    break;
                case URL_ERROR:
                    ToastUtil.show(SplashActivity.this, "url异常");
                    enterHome();
                    break;
                case IO_ERROR:
                    ToastUtil.show(SplashActivity.this, "io异常");
                    enterHome();
                    break;
                case JSON_ERROR:
                    ToastUtil.show(SplashActivity.this, "json异常");
                    enterHome();
                    break;
            }
        }
    };
    private String mVersionDes;
    private String mDownloadUrl;

    private void showUpdateDialog() {
        //对话框是依赖于activity存在的
//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("版本更新");
        builder.setMessage(mVersionDes);
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //下载apk,apk链接地址,downloadUrl
                downloadApk();
            }
        });
        builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //取消对话框,进入主界面
                enterHome();
            }
        });
        builder.show();
    }

    private void downloadApk() {
        //apk下载链接地址,放置apk的所在路径

        //1.判断sd卡是否可用,是否挂载上
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2.获取sd路径
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "mobilesafe74.apk";
            //3.发送请求,获取apk,并且放置到指定路径
            HttpUtils httpUtils = new HttpUtils();
            //4.发送请求,传递参数(下载地址,下载应用位置)
            httpUtils.download(mDownloadUrl, path, new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    //下载成功(下载过后的放置在sd卡中的apk)
                    Log.i("hehe", "下载成功");
                    File file = responseInfo.result;
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    //下载失败
                    Log.i("hehe", "下载失败");
                }

                //刚刚开始下载
                @Override
                public void onStart() {
                    Log.i("hehe", "刚刚开始下载");
                    super.onStart();
                }

                //下载过程中的方法(下载apk总大小,当前的下载位置,是否正在下载)
                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    Log.i("hehe", "下载中......");
                    Log.i("hehe", "total = " + total);
                    Log.i("hehe", "current = " + current);
                    super.onLoading(total, current, isUploading);
                }
            });
        }
    }

    /**
     * 进入应用程序的主界面
     */
    private void enterHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);


        //在开启一个新的界面后,将导航界面关闭(导航界面只可见一次)
        finish();
    }


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

        checkVersion();
    }

    /**
     * 检测版本号
     */
    private void checkVersion() {
        new Thread() {
            public void run() {
                //发送请求获取数据
                //高效
                Message msg = Message.obtain();
                long startTime = System.currentTimeMillis();
                try {
                    //1.封装url地址
                    URL url = new URL("http://192.168.31.149/update74.json");
                    //2.开启一个链接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //3.设置常见请求参数(请求头)

                    //请求超时
                    connection.setConnectTimeout(2000);

                    //读取超时
                    connection.setReadTimeout(2000);

                    //默认就是get请求方式
//                    connection.setRequestMethod("POST");

                    //4.获取响应码
                    if (connection.getResponseCode() == 200) {
                        //5.以留的形式,将数据获取下来
                        InputStream is = connection.getInputStream();
                        //6.将流转换成字符串(工具类分装)
                        String json = StreamUtil.streamToString(is);
//                        Log.i("hehe", json);
                        //7.json解析

                        JSONObject jsonObject = new JSONObject(json);
                        mDownloadUrl =  jsonObject.getString("downloadUrl");
                        String versionCode = jsonObject.getString("versionCode");
                        mVersionDes = jsonObject.getString("versionDes");
                        String versionName = jsonObject.getString("versionName");
                        Log.i("hehe", mDownloadUrl);
                        Log.i("hehe", versionCode);
                        Log.i("hehe", mVersionDes);
                        Log.i("hehe", versionName);

                        //8.对比版本号(服务器版本号>本地版本号,提示用户更新)
                        if (mLocalVersionCode<Integer.parseInt(versionCode)) {
                            //提示用户更新,弹出对话框(UI)
                            msg.what = UPDATE_VERSION;
                        }
                        else {
                            //进入应用主界面
                            msg.what = ENTER_HOME;
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    msg.what = URL_ERROR;
                } catch (IOException e) {
                    msg.what = IO_ERROR;
                    e.printStackTrace();
                } catch (JSONException e) {
                    msg.what = JSON_ERROR;
                    e.printStackTrace();
                } finally {
                    //指定睡眠时间,请求网络的时常超过4秒不做处理
                    //请求网络的时长小于4秒,强制让其睡眠满4秒钟
                    long endTime = System.currentTimeMillis();
                    if (endTime - startTime < 2000) {
                        try {
                            Thread.sleep(2000 - (endTime - startTime));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
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
