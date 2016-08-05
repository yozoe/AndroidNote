package com.example.mobilesafe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

/**
 * Created by wangdong on 16/8/6.
 */
public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        TextView textView = new TextView(this);
        textView.setText("TestActivity");
        setContentView(textView);
    }
}
