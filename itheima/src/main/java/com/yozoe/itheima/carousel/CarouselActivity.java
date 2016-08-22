package com.yozoe.itheima.carousel;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yozoe.itheima.R;

/**
 * Created by wangdong on 16/8/22.
 */
public class CarouselActivity extends Activity {
    private ViewPager viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carouse);

        initViews();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        viewPage.setAdapter(new MyAdapter());
    }

    private void initData() {

    }

    private void initViews() {
        viewPage = (ViewPager) findViewById(R.id.viewpager);
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }
}
