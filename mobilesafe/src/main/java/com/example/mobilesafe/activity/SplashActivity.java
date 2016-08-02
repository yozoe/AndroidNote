package com.example.mobilesafe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                //即使用户点了取消也进入应用程序主界面
                enterHome();
                dialogInterface.dismiss();
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
            //4.发送请求,传递参数(下载地址,下载应用位置)
            RequestParams params = new RequestParams(mDownloadUrl);
            x.http().get(params, new Callback.ProgressCallback<File>() {
                @Override
                public void onWaiting() {
                    Log.i("hehe", "on waiting");
                }

                @Override
                public void onStarted() {
                    Log.i("hehe", "on started");
                }

                @Override
                public void onLoading(long total, long current, boolean isDownloading) {
                    Log.i("hehe", "current = " + current);
                }

                @Override
                public void onSuccess(File result) {
                    //下载成功(下载过后的放置在sd卡中的apk)
                    Log.i("hehe", "下载成功");
//                    File file = responseInfo.result;
                    File file = result;
                    //提示用户安装
                    installApk(file);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
//                    Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(CancelledException cex) {
//                    Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFinished() {
                    Log.i("hehe", "finished");
                }

            });
        }
    }

    /**
     * 安装对应apk
     * @param file 安装文件
     */
    private void installApk(File file) {

        //版本1从android studio上运行的引用,使用的是bin目录下的应用,使用~/.android/debug.keystore
        //版本2单独打包生成的是签名文件是mobilesafe.jks
        //所以用mobilesafe.jks打两个版本的包
        //用adb install *.apk安装第一个版本
        //将第二个版本上传到服务器 再更新即可
        //包名一直签名不一致安装失败
        //签名一直包名不一直生成两个应用

        //系统应用界面,源码,安装apk入口
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        //文件作为数据源
//        intent.setData(Uri.fromFile(file));
//        intent.setType("application/vnd.android.package-archive");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        startActivity(intent);
        startActivityForResult(intent, 0);
    }

    /**
     * 开一个activity后返回结果调用的
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        enterHome();
        super.onActivityResult(requestCode, resultCode, data);
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
