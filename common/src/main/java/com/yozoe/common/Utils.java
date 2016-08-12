package com.yozoe.common;

/**
 * Created by wangdong on 16/8/12.
 */
public class Utils {

    public static int dip2px(float dipValue) {
        float scale = getScreenDensity();
        return (int) (scale * dipValue + 0.5);
    }

    public static float getScreenDensity() {
        return BaseApplication.getContext().getResources().getDisplayMetrics().density;
    }
}
