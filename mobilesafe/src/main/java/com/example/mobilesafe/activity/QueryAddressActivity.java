package com.example.mobilesafe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobilesafe.R;


/**
 * Created by wangdong on 16/8/15.
 */
public class QueryAddressActivity extends Activity {
    private EditText et_phone;
    private Button bt_query;
    private TextView tv_result ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_address);

        et_phone = (EditText) findViewById(R.id.et_phone);
        bt_query = (Button) findViewById(R.id.bt_query);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }
}
