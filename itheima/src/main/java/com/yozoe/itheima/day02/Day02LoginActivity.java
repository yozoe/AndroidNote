package com.yozoe.itheima.day02;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.yozoe.itheima.R;

import java.io.File;
import java.util.Map;

/**
 * Created by wangdong on 16/8/7.
 */
public class Day02LoginActivity extends Activity implements View.OnClickListener {
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_rem;
    private Button bt_login;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_day02_login);

        mContext = this;

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        cb_rem = (CheckBox) findViewById(R.id.cb_rem);
        bt_login = (Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(this);


        Map<String, String> map = UserInfoUtil.getUserInfo(mContext);
        if (map != null) {
            String username = map.get("username");
            String password = map.get("password");
            et_username.setText(username);
            et_password.setText(password);
            cb_rem.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                login();
                break;
        }
    }

    private void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        boolean isrem = cb_rem.isChecked();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isrem) {
            boolean result = UserInfoUtil.saveUserInfo(mContext, username, password);


            //判断sdcard是否正常
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //sdkcard状态是没有挂在
                Toast.makeText(mContext, "sdcard不存在或未挂在", Toast.LENGTH_SHORT).show();;
            }

            //判断sdcard存储空间是否满足文件的存储

            File sdcard_filedir = Environment.getExternalStorageDirectory();//得到sdcard的目录作为一个文件对象
            long usablesSpace = sdcard_filedir.getUsableSpace();//获取文件目录对象剩余空间
            long totalSpace = sdcard_filedir.getTotalSpace();

            String usableSpace_str = Formatter.formatFileSize(mContext, usablesSpace);
            String totalSpace_str = Formatter.formatFileSize(mContext, totalSpace);

            if (usablesSpace < 1024 * 1024 * 200) {//判断剩余空间是否小于200M
                Toast.makeText(mContext, "sdcard剩余空间不足,无法满足下载,剩余空间为" + usableSpace_str, Toast.LENGTH_SHORT).show();
            }

            if (result) {
                Toast.makeText(mContext, "用户名密码保存成功", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(mContext, "用户名密码保存失败", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(mContext, "无需保存", Toast.LENGTH_SHORT).show();
        }
    }

}

/*
/data/data/: context.getFileDir().getPath() 是一个应用程序的私有目录,只有当前应用程序有权限访问读写,安全性要求比较高的数据,size比较小的
/sdcard: Environment.getExternalStorageDirectory() 是一个外部存储目录,声明权限就可以访问,存放安全性不高的数据,size比较大的
 */