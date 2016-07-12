package com.yozoe.pattern.command;

import android.graphics.Path;
import android.util.Log;

/**
 * Created by wangdong on 16/7/8.
 */
public class NormalBrush implements IBrush {


    @Override
    public void down(Path path, float x, float y) {
        path.moveTo(x, y);

    }

    @Override
    public void move(Path path, float x, float y) {
        path.lineTo(x, y);

    }

    @Override
    public void up(Path path, float x, float y) {

    }
}
