package com.yozoe.myworld.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yozoe.myworld.MyWorldApplication;
import com.yozoe.myworld.R;
import com.yozoe.myworld.entity.HomeConfigEntity;
import com.yozoe.myworld.util.FileUtil;
import com.yozoe.myworld.view.FixScrollView;
import com.yozoe.myworld.view.HomeBlocksLayout;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by wangdong on 16/8/8.
 */
public class FirstFragment extends BaseFragment {

    private FixScrollView mFixScrollView;
    private LinearLayout mLayoutHomeConfig;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("hehe", "first fragment onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("hehe", "first fragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_first, null);
        mFixScrollView = (FixScrollView) view.findViewById(R.id.sv_content);
        mLayoutHomeConfig = (LinearLayout) view.findViewById(R.id.layout_home_config);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        initModules(0);
    }

    public HomeConfigEntity getHomeConfig() {
        return null;
    }

    private void initModules(int type) {
        mLayoutHomeConfig.removeAllViews();
        mFixScrollView.scrollTo(0, 0);
        try {
            String homeCfgContent = FileUtil.getContent(MyWorldApplication.getContext().getResources().getAssets().open("homeconfig"));
            HomeConfigEntity homeConfigEntity = new Gson().fromJson(homeCfgContent, new TypeToken<HomeConfigEntity>(){}.getType());

            for (HomeConfigEntity.HomeConfigModule module : homeConfigEntity.getModules()) {
                if (module.getModuleType().equals(HomeConfigEntity.HomeConfigModule.MODULE_BLOCKS)) {
                    HomeBlocksLayout blocksLayout = new HomeBlocksLayout(mContext);
                    blocksLayout.setModule(module);
                    mLayoutHomeConfig.addView(blocksLayout);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
