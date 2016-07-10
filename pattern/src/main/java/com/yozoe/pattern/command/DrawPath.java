package com.yozoe.pattern.command;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by wangdong on 16/7/10.
 */
public class DrawPath implements IDraw {

    public Path path;
    public Paint paint;


    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public void undo() {

    }
}
