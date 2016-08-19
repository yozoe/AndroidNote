package com.example.test.activity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.receiver.DeviceAdmin;
import com.example.test.R;

public class MainActivity extends AppCompatActivity {

    Button bt_start;
    Button bt_lock;
    Button bt_wipedata;
    Button bt_uninstall;
    ComponentName mDeviceAdminSample;
    private DevicePolicyManager mDPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_start = (Button) findViewById(R.id.bt_start);
        bt_lock = (Button) findViewById(R.id.bt_lock);
        bt_wipedata = (Button) findViewById(R.id.bt_wipedata);
        bt_uninstall = (Button) findViewById(R.id.bt_uninstall);

        //(上下文环境, 广播接收者对应的字节码文件),组件对象可以作为是否激活的判断标志
        mDeviceAdminSample = new ComponentName(this, DeviceAdmin.class);
        mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "设备管理器");
                startActivity(intent);
            }
        });

        bt_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //是否开启了判断
                if (mDPM.isAdminActive(mDeviceAdminSample)) {
                    mDPM.lockNow();
                    //锁屏同时去设置密码
                    mDPM.resetPassword("123", 0);
                }
                else {
                    Toast.makeText(getApplicationContext(), "请先激活", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_wipedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDPM.wipeData(0);//手机数据
//                mDPM.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);//手机SD卡数据
            }
        });

        /*
        在设备管理器中没有激活,可以卸载
        在设备管理器中有激活,不可以卸载,系统会提示取消在设备管理器中激活,然后才可以卸载
        查看packageInstaller源码,找到uninstallActivity源码,匹配对应的action,category,data去卸载指定应用
         */
        bt_uninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.DELETE");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        });
    }
}
