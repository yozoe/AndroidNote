package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.mobilesafe.R;

/**
 * Created by wangdong on 16/8/6.
 */
public class Setup1Activity extends Activity {
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);

        //2.创建手势管理对象,用作管理在onTouchEvent(event)传递过来的手势动作
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() - e2.getX() > 0) {
                    //由右向左,移动到下一页
                    Intent intent = new Intent(getApplicationContext(), Setup2Activity.class);
                    startActivity(intent);
                    finish();

                    overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
                }

                if (e1.getX() - e2.getX() < 0) {
                    //由左向右,移动到上一页
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    public void nextPage(View view) {
        Intent intent = new Intent(getApplicationContext(), Setup2Activity.class);
        startActivity(intent);
        finish();

        overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
    }

    //1.坚挺屏幕上响应的事件类型(按下(1次),移动(多次),抬起(1次))
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //3.通过手势处理类,接收多种类型的事件,用作处理
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
