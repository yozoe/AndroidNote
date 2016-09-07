package com.example.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.R;
import com.example.test.view.DrawTestView;

/**
 * Created by wangdong on 16/9/7.
 */
public class DrawTestActivity extends Activity {

    private DrawTestView testDrawView;
    private Button testDrawButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_test);

        testDrawView = (DrawTestView) findViewById(R.id.view_draw_test);
        testDrawButton = (Button) findViewById(R.id.btn_draw);

        testDrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDrawView.invalidate();
            }
        });
    }
}
