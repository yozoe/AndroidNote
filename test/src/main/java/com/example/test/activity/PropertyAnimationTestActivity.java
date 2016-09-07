package com.example.test.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.test.R;

/**
 * Created by wangdong on 16/9/6.
 */
public class PropertyAnimationTestActivity extends Activity implements View.OnClickListener {

    private View testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_test);

        testView = findViewById(R.id.view_test);

        findViewById(R.id.btn_object_animator).setOnClickListener(this);
        findViewById(R.id.btn_wrapper_test).setOnClickListener(this);
        findViewById(R.id.btn_holder).setOnClickListener(this);
        findViewById(R.id.btn_value_animator).setOnClickListener(this);
        findViewById(R.id.btn_animator_set).setOnClickListener(this);
        findViewById(R.id.btn_animator_xml).setOnClickListener(this);
        findViewById(R.id.btn_view_animate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_object_animator:
                objectAnimator();
                break;
            case R.id.btn_wrapper_test:
                wrapperAnimator();
                break;
            case R.id.btn_holder:
                holderTest();
                break;
            case R.id.btn_value_animator:
                valueAnimatorTest();
                break;
            case R.id.btn_animator_set:
                animatorSetTest();
                break;
            case R.id.btn_animator_xml:
                animatorXmlTest();
                break;
            case R.id.btn_view_animate:
                viewAnimateTest();
                break;
        }
    }

    private void viewAnimateTest() {
        testView.animate().alpha(0).y(300).setDuration(300).withStartAction(new Runnable() {
            @Override
            public void run() {
                Log.i("hehe", "什么叫with start");
//                13691446871
            }
        }).withEndAction(new Runnable() {
            @Override
            public void run() {
                Log.i("hehe", "什么叫with end");
            }
        }).start();
    }

    private void animatorXmlTest() {
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scalex);
        anim.setTarget(testView);
        anim.start();
    }

    private void animatorSetTest() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(testView, "translationX", 300f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(testView, "scaleX", 1f, 0f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(testView, "scaleY", 1f, 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        //playSequentially() play().with() before() after()
        set.playTogether(animator1, animator2, animator3);
        set.start();
    }

    private void valueAnimatorTest() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setTarget(testView);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                Log.i("hehe", String.valueOf(value));
            }
        });
    }

    private void holderTest() {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleT", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(testView, pvh1, pvh2, pvh3).setDuration(1000).start();
    }

    private void wrapperAnimator() {
        ViewWrapper wrapper = new ViewWrapper(testView);
        ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(5000).start();
    }

    private void objectAnimator() {
        ObjectAnimator animator = new ObjectAnimator().ofFloat(testView, "translationX", 300);
        animator.setDuration(3000);
        animator.start();

    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
