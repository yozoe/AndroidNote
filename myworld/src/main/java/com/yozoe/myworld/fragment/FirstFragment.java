package com.yozoe.myworld.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yozoe.myworld.MyWorldApplication;
import com.yozoe.myworld.R;
import com.yozoe.myworld.entity.HomeConfigEntity;
import com.yozoe.myworld.util.FileUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangdong on 16/8/8.
 */
public class FirstFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            String homeCfgContent = FileUtil.getContent(MyWorldApplication.getContext().getResources().getAssets().open("homeconfig"));

            HomeConfigEntity homeConfigEntity = new Gson().fromJson(homeCfgContent, new TypeToken<HomeConfigEntity>(){}.getType());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
        return view;
    }

    public HomeConfigEntity getHomeConfig() {
        return null;
    }
}
