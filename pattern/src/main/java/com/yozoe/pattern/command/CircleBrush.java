package com.yozoe.pattern.command;

import android.graphics.Path;

/**
 * Created by wangdong on 16/7/8.
 */
public class CircleBrush implements IBrush {
    @Override
    public void down(Path path, float x, float y) {

    }

    @Override
    public void move(Path path, float x, float y) {
        path.addCircle(x, y, 10, Path.Direction.CW);
    }

    @Override
    public void up(Path path, float x, float y) {

    }
}
