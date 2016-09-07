package com.example.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by wangdong on 16/9/7.
 */
public class DrawTestView extends View {

    public DrawTestView(Context context) {
        super(context);
    }

    public DrawTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.i("hehe", "on draw");
//
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);
//        int a = (int) (Math.random()*1000);
////        canvas.drawRect(50, 50, a, a, paint);
//
//        canvas.drawLines(new float[]{
//                1.f, 100.f, 500.f, 400.f,
//                50.f, 200.f, 300.f, 100.f}, paint);

        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
//        canvas.drawCircle();
    }
}
