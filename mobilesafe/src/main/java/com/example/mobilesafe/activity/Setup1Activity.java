package com.example.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;

import com.example.mobilesafe.R;

/**
 * Created by wangdong on 16/8/6.
 */
public class Setup1Activity extends BaseSetupActivity {
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);
    }

    @Override
    protected void showNextPage() {
        Intent intent = new Intent(getApplicationContext(), Setup2Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
    }

    @Override
    protected void showPrePage() {
        //空实现
    }


}
