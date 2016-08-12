package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.mobilesafe.R;
import com.example.mobilesafe.util.ConstantValue;
import com.example.mobilesafe.util.SpUtil;
import com.example.mobilesafe.util.ToastUtil;

/**
 * Created by wangdong on 16/8/7.
 */
public class Setup4Activity extends BaseSetupActivity {
    private CheckBox cb_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);

        initUI();

        SpUtil.getBoolean(this, ConstantValue.OPEN_SECURITY, false);
    }

    @Override
    protected void showNextPage() {
        boolean open_security = SpUtil.getBoolean(this, ConstantValue.OPEN_SECURITY, false);
        if (open_security) {
            Intent intent = new Intent(getApplication(), SetupOverActivity.class);
            startActivity(intent);
            finish();
            SpUtil.putBoolean(this, ConstantValue.SETUP_OVER, true);
            overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
        }
        else {
            ToastUtil.show(getApplicationContext(), "请开启防盗保护");
        }
    }

    @Override
    protected void showPrePage() {
        Intent intent = new Intent(getApplication(), Setup3Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.pre_in_anim, R.anim.pre_out_anim);
    }

    private void initUI() {
        cb_box = (CheckBox) findViewById(R.id.cb_box);
        boolean open_security = SpUtil.getBoolean(this, ConstantValue.OPEN_SECURITY, false);
        cb_box.setChecked(open_security);

        if (open_security) {
            cb_box.setText("安全设置已开启");
        }
        else {
            cb_box.setText("安全设置已关闭");
        }
        cb_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SpUtil.putBoolean(Setup4Activity.this, ConstantValue.OPEN_SECURITY, b);
                if (b) {
                   cb_box.setText("安全设置已开启");
                }
                else {
                    cb_box.setText("安全设置已关闭");
                }
            }
        });

    }

}
