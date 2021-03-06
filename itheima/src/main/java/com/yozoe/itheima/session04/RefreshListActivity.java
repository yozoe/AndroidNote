package com.yozoe.itheima.session04;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.yozoe.itheima.R;
import com.yozoe.itheima.session04.ui.RefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdong on 16/8/27.
 */
public class RefreshListActivity extends Activity {
    private RefreshListView listView;
    private List<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_refresh_list);

//        Button button = new Button(this);
//        button.setText("我是按钮");

        listView = (RefreshListView) findViewById(R.id.listView);

//        listView.addHeaderView(button);

        listData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            listData.add("这是一条list数据:" + i);
        }
        listView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            EditText textView = new EditText(parent.getContext());
            textView.setText(listData.get(position));
            textView.setTextSize(18.f);
            return textView;
        }

    }
}
