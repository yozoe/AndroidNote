package com.example.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.test.R;

/**
 * Created by wangdong on 16/8/30.
 */
public class EventTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_test);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("hehe", "activity onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("hehe", "activity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
//        return false;
//        return true;
    }

}
