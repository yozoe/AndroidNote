package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mobilesafe.R;
import com.example.mobilesafe.util.ConstantValue;
import com.example.mobilesafe.util.SpUtil;

/**
 * Created by wangdong on 16/8/7.
 */
public class Setup4Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
    }

    public void nextPage(View view) {
        Intent intent = new Intent(getApplication(), SetupOverActivity.class);
        startActivity(intent);
        finish();

        SpUtil.putBoolean(this, ConstantValue.SETUP_OVER, true);
    }

    public void prePage(View view) {
        Intent intent = new Intent(getApplication(), Setup3Activity.class);
        startActivity(intent);
        finish();
    }
}
