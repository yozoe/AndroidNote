package com.yozoe.pattern.command;

import android.graphics.Canvas;

/**
 * Created by wangdong on 16/7/8.
 */
public interface IDraw {
    void draw(Canvas canvas);
    void undo();
}
