package com.yozoe.itheima.session04;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.yozoe.itheima.R;
import com.yozoe.itheima.session04.ui.ToggleView;

/**
 * Created by wangdong on 16/8/25.
 */
public class ToggleActivity extends Activity {

    private ToggleView toggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle);

        toggleView = (ToggleView) findViewById(R.id.toggleView);
//        toggleView.setSwitchBackgroundResource(R.drawable.switch_background);
//        toggleView.setSlideButtonResource(R.drawable.slide_button);
//        toggleView.setSwitchState(false);

        toggleView.setOnSwitchStateUpdateListener(new ToggleView.OnSwitchStateUpdateListener() {

            @Override
            public void onStateUpdate(boolean state) {
                Toast.makeText(getApplicationContext(), "state: " + state, Toast.LENGTH_SHORT).show();
            }
        });
    }

}