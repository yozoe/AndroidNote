package com.yozoe.itheima.session04.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.yozoe.itheima.R;

/**
 * Created by wangdong on 16/8/27.
 */
public class RefreshListView extends ListView {


    private View mHeaderView;

    public RefreshListView(Context context) {
        super(context);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initHeaderView();
    }

    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.layout_header_list, null);
        addHeaderView(mHeaderView);
    }
}
