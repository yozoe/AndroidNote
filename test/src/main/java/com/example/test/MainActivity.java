package com.example.test;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        //(上下文环境, 广播接收者对应的字节码文件)
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
                mDPM.lockNow();
            }
        });
    }
}
