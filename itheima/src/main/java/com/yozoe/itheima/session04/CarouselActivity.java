package com.yozoe.itheima.session04;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yozoe.itheima.R;

import java.util.ArrayList;

/**
 * Created by wangdong on 16/8/22.
 */
public class CarouselActivity extends Activity {
    private ViewPager viewPage;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private String[] contentDesc;
    private TextView tv_desc;
    private int previousSelectPosition;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carouse);

        initViews();
        initData();
        initAdapter();

        //在Timer和ScheduledExecutorService间决择
/*
quartzWeb框架threadJDK
　　java.util.Timer计时器有管理任务延迟执行("如1000ms后执行任务")以及周期性执行("如每500ms执行一次该任务")。
  但是，Timer存在一些缺陷，因此你应该考虑使用ScheduledThreadPoolExecutor作为代替品,Timer对调度的支持是基于绝对时间,而不是相对时间的，
  由此任务对系统时钟的改变是敏感的;ScheduledThreadExecutor只支持相对时间。

Timer的另一个问题在于，如果TimerTask抛出未检查的异常，Timer将会产生无法预料的行为。
  Timer线程并不捕获异常，所以TimerTask抛出的未检查的异常会终止timer线程。
  这种情况下，Timer也不会再重新恢复线程的执行了;它错误的认为整个Timer都被取消了。此时，已经被安排但尚未执行的TimerTask永远不会再执行了，新的任务也不能被调度了。
*/


        //开启轮询
        new Thread() {
            @Override
            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //往下跳一位
                            viewPage.setCurrentItem(viewPage.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    private void initAdapter() {
        viewPage.setAdapter(new MyAdapter());
        tv_desc.setText(contentDesc[0]);
        previousSelectPosition = 0;
        ll_point_container.getChildAt(0).setEnabled(true);

        //默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        viewPage.setCurrentItem(pos);
    }

    private void initData() {
        int[] imageResIds = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e
        };

        contentDesc = new String[] {
                "功利不低俗,我就不能低俗",
                "朴树又回来啦!再唱经典老歌引万人大合唱",
                "揭秘北京电影如何升级",
                "乐视网TV版大派送",
                "热血屌丝的反杀",
        };

        imageViewList = new ArrayList<>();
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);

            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.selector_bg_point);

            layoutParams = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            pointView.setEnabled(false);
            layoutParams.topMargin = 10;
            ll_point_container.addView(pointView, layoutParams);
        }
    }

    private void initViews() {
        viewPage = (ViewPager) findViewById(R.id.viewpager);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滚动是调用
            }

            @Override
            public void onPageSelected(int position) {
                //新条目被选中

                int newPosition = position % imageViewList.size();
                tv_desc.setText(contentDesc[newPosition]);
                ll_point_container.getChildAt(previousSelectPosition).setEnabled(false);
                ll_point_container.getChildAt(newPosition).setEnabled(true);
                previousSelectPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滚动变化
            }
        });
        ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
//            return imageViewList.size();
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            //当滑到新的条目,又返回来,view是否可以被复用
            //返回判断规则
            return view == object;
        }

        //1.返回要显示的内容
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //container 容器:ViewPager
            //position 当前药显示条目的位置
            Log.i("hehe", "创建" + position);
            int newPosition = position % imageViewList.size();
            ImageView imageView = imageViewList.get(newPosition);
            container.addView(imageView);
            return imageView;
//            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
            int newPosition = position % imageViewList.size();
            Log.i("hehe", "销毁" + newPosition);
        }
    }
}
