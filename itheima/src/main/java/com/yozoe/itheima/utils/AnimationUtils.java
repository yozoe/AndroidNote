package com.yozoe.itheima.utils;

import android.text.Layout;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by wangdong on 16/8/21.
 */
public class AnimationUtils {
    public static void rotateOutAnim(RelativeLayout layout, long delay) {

        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            layout.getChildAt(i).setEnabled(false);
        }

        RotateAnimation ra = new RotateAnimation(
                0,//起始角度
                -180f,//结束角度
                Animation.RELATIVE_TO_SELF,//相对x坐标点
                0.5f,
                Animation.RELATIVE_TO_SELF,//相对y坐标点
                1.0f);
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(delay);
        ra.setAnimationListener(new MyAnimationListener());
        layout.startAnimation(ra);
    }

    public static void rotateInAnim(RelativeLayout layout, long delay) {

        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            layout.getChildAt(i).setEnabled(true);
        }

        RotateAnimation ra = new RotateAnimation(
                -180,//起始角度
                0f,//结束角度
                Animation.RELATIVE_TO_SELF,//相对x坐标点
                0.5f,
                Animation.RELATIVE_TO_SELF,//相对y坐标点
                1.0f);
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setAnimationListener(new MyAnimationListener());
        layout.startAnimation(ra);
    }

    public static int runningAnimationCount = 0;

    static class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            runningAnimationCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            runningAnimationCount--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
