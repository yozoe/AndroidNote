package com.yozoe.pattern.command;

import android.graphics.Path;

/**
 * Created by wangdong on 16/7/8.
 */
public interface IBrush {

    void down(Path path, float x, float y);

    void move(Path path, float x, float y);

    void up(Path path, float x, float y);

}
