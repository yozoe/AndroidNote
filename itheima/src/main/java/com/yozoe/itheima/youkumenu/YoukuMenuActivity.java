package com.yozoe.itheima.youkumenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.yozoe.itheima.R;
import com.yozoe.itheima.utils.AnimationUtils;

/**
 * Created by wangdong on 16/8/21.
 */
public class YoukuMenuActivity extends Activity implements View.OnClickListener {

    private RelativeLayout rl_level1;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;
    boolean isLevel1Display = true;
    boolean isLevel2Display = true;
    boolean isLevel3Display = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youku_menu);

        initViews();
    }

    private void initViews() {
        findViewById(R.id.ib_home).setOnClickListener(this);
        findViewById(R.id.ib_menu).setOnClickListener(this);

        rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rl_level3=  (RelativeLayout) findViewById(R.id.rl_level3);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {



        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if (AnimationUtils.runningAnimationCount > 0) {
            return;
        }
        switch (v.getId()) {
            case R.id.ib_home:
                Log.i("hehe", "ib_home");
                if (isLevel2Display) {

                    long delay = 0;

                    if(isLevel3Display) {
                        AnimationUtils.rotateOutAnim(rl_level3, 0);
                        delay += 200;
                        isLevel3Display = false;
                    }

                    AnimationUtils.rotateOutAnim(rl_level2, delay);
                }
                else {
                    AnimationUtils.rotateInAnim(rl_level2, 0);
                }
                isLevel2Display = !isLevel2Display;
                break;
            case R.id.ib_menu:

                Log.i("hehe", "ib_menu");
                if (isLevel3Display) {
                    AnimationUtils.rotateOutAnim(rl_level3, 0);
                }
                else {
                    AnimationUtils.rotateInAnim(rl_level3, 0);
                }
                isLevel3Display = !isLevel3Display;
                break;
        }
    }
}
