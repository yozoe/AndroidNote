package com.example.test.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.example.test.R;

/**
 * Created by wangdong on 16/9/7.
 */
public class LayoutAnimationTestActivity extends Activity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation_test);

        mContext = this;

        final LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(2000);

        LayoutAnimationController lac = new LayoutAnimationController(sa, 0.5f);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        ll.setLayoutAnimation(lac);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = new View(mContext);
                view.setBackgroundColor(Color.parseColor("#000000"));
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(100, 100);
                view.setLayoutParams(lp);
                ll.addView(view);
            }
        });
    }
}
