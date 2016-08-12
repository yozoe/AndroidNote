package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobilesafe.R;
import com.example.mobilesafe.util.ConstantValue;
import com.example.mobilesafe.util.SpUtil;

/**
 * Created by wangdong on 16/8/6.
 */
public class SetupOverActivity extends Activity {
    private TextView tv_reset_setup;
    private TextView tv_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean setup_over = SpUtil.getBoolean(this, ConstantValue.SETUP_OVER, false);
        //只要密码输入成功,并且四个导航界面设置完成----->设置完成功能列表界面
        //只要密码输入成功,四个导航界面设没有置完成----->跳转到导航界面第一个

        if (setup_over) {
            setContentView(R.layout.activity_setup_over);
            initUI();
        }
        else {
            Intent intent = new Intent(this, Setup1Activity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initUI() {
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_reset_setup = (TextView) findViewById(R.id.tv_reset_setup);
        tv_reset_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
