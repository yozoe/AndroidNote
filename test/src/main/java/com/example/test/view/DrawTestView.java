package com.example.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangdong on 16/9/7.
 */
public class DrawTestView extends View {

    private int mHeight, mWidth;

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

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paintCircle);


        Paint painDegree = new Paint();
        paintCircle.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                painDegree.setStrokeWidth(5);
                painDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 60, painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, mWidth / 2 - painDegree.measureText(degree) / 2, mHeight / 2 - mWidth / 2 + 90, painDegree);
            }
            else {
                painDegree.setStrokeWidth(3);
                painDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 30, painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, mWidth / 2 - painDegree.measureText(degree) / 2, mHeight / 2 - mWidth / 2 + 60, painDegree);
            }
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }

        Paint paintPointer = new Paint();
        paintPointer.setStrokeWidth(30);
        canvas.drawPoint(mWidth / 2, mHeight / 2, paintPointer);

        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        canvas.restore();
    }
}
