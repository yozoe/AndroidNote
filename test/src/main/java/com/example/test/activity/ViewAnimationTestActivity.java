package com.example.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.test.R;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ViewAnimationTestActivity extends Activity {

    private View testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation_test);

        testView = findViewById(R.id.view_test);

        findViewById(R.id.btn_alpha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alphaAnimation();
            }
        });

        findViewById(R.id.btn_rotate).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rotateAnimation();
            }
        });

        findViewById(R.id.btn_translate).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                translateAnimation();
            }
        });

        findViewById(R.id.btn_scale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleAnimation();
            }
        });

        findViewById(R.id.btn_animation_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationSet();
            }
        });
    }

    private void animationSet() {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(4000);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);

        as.addAnimation(aa);

        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);

        as.addAnimation(ra);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(1000);

        as.addAnimation(sa);

        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("hehe", "on animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("hehe", "on animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("hehe", "on animation repeat");
            }
        });

        testView.startAnimation(as);
    }

    private void alphaAnimation() {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        testView.startAnimation(aa);
    }

    private void rotateAnimation() {
//        RotateAnimation ra = new RotateAnimation(0, 360, 100, 100);
        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);
        testView.startAnimation(ra);
    }

    private void translateAnimation() {
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 300);
        ta.setDuration(1000);
        testView.startAnimation(ta);
    }

    private void scaleAnimation() {
//        ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(1000);
        testView.startAnimation(sa);
    }
}
