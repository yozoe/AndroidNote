package com.yozoe.common;

import java.util.List;

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

    public static int getScreenWidth() {
        return BaseApplication.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return BaseApplication.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.toString().trim().length() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isEmpty(final List<? extends Object> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

}
