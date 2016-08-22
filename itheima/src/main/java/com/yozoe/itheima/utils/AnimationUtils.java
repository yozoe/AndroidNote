package com.yozoe.itheima.utils;

import android.text.Layout;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by wangdong on 16/8/21.
 */
public class AnimationUtils {
    public static void rotateOutAnim(RelativeLayout layout) {
        RotateAnimation ra = new RotateAnimation(
                0,//起始角度
                -180f,//结束角度
                Animation.RELATIVE_TO_SELF,//相对x坐标点
                0.5f,
                Animation.RELATIVE_TO_SELF,//相对y坐标点
                1.0f);
        ra.setDuration(500);
        layout.setAnimation(ra);
    }
}
