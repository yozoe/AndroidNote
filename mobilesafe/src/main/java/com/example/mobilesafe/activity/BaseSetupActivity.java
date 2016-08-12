package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.mobilesafe.R;

/**
 * Created by wangdong on 16/8/11.
 */
public abstract class BaseSetupActivity extends Activity {
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //2.创建手势管理对象,用作管理在onTouchEvent(event)传递过来的手势动作
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
                if (e1.getX() - e2.getX() > 0) {
                    //由右向左,移动到下一页
                    showNextPage();
                }
                if (e1.getX() - e2.getX() < 0) {
                    //由左向右,移动到上一页
                    showPrePage();
                }
                return super.onFling(e1, e2, v, v1);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //下一页的抽象方法,由子类决定具体跳转到哪个界面
    protected abstract void showNextPage();

    //上一页的抽象方法,由子类决定具体跳转到哪个界面
    protected abstract void showPrePage();

    //点击下一页按钮的时候,根据子类的showNextPage方法响应跳转
    public void nextPage(View view) {
        showNextPage();
    }

    public void prePage(View view) {
        showPrePage();
    }
}
