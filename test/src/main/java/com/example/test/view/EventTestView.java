package com.example.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangdong on 16/8/30.
 */
public class EventTestView extends ViewGroup {
    public EventTestView(Context context) {
        super(context);
    }

    public EventTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("hehe", "view onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("hehe", "view onInterceptTouchEvent");
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("hehe", "view dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
//        return false;
//        return true;
    }
}
