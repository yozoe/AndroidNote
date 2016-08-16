package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by wangdong on 16/8/16.
 */
public class ViewStubTestActivity extends Activity {
    private ViewStub view_stub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub_test);

        view_stub = (ViewStub) findViewById(R.id.comment_stub);
        findViewById(R.id.show_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_stub.inflate();
//                view_stub.setVisibility(View.VISIBLE);
            }
        });
    }
}
