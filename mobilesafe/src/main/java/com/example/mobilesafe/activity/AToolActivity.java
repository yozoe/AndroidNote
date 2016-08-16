package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobilesafe.R;

/**
 * Created by wangdong on 16/8/15.
 */
public class AToolActivity extends Activity {
    private TextView tv_query_phone_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atool);

        initPhoneAddress();
    }

    private void initPhoneAddress() {
        tv_query_phone_address = (TextView) findViewById(R.id.tv_query_phone_address);
        tv_query_phone_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QueryAddressActivity.class));
            }
        });
    }
}
