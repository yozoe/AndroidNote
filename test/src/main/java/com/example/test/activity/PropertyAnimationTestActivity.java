package com.example.test.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getWidth()) {
            case R.id.btn_object_animator:
                objectAnimator();
                break;
        }
    }

    private void objectAnimator() {
        ObjectAnimator animator = new ObjectAnimator().ofFloat(testView, "translationX", 300);
    }
}
