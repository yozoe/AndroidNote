package com.example.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.R;
import com.example.test.anim.Rotate3dAnimation;

/**
 * Created by Administrator on 2016/9/4.
 */
public class AnimationTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation_test);

        final Button btn = (Button) findViewById(R.id.btn_test);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rotate3dAnimation r = new Rotate3dAnimation(50.f, 100.f, 50.f, 50.f, 10.f, false);
                r.setDuration(2000);
                btn.startAnimation(r);
            }
        });
    }
}
