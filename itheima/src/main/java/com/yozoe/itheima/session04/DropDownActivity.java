package com.yozoe.itheima.session04;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yozoe.itheima.R;

import java.util.ArrayList;

/**
 * Created by wangdong on 16/8/24.
 */
public class DropDownActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private EditText et_input;
    private ArrayList<String> datas;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropdown);

        findViewById(R.id.ib_dropdown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });

        et_input = (EditText) findViewById(R.id.et_input);
    }

    private void showPopupWindow() {
        initListView();
        popupWindow = new PopupWindow(listView, et_input.getWidth(), 600);

        //外部可触摸
//        popupWindow.setOutsideTouchable(true);

        //设置空背景响应点击事件
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置可获取焦点
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(et_input, 0, 0);
    }

    private void initListView() {
        listView = new ListView(this);
        listView.setDividerHeight(0);
        listView.setBackgroundResource(R.drawable.listview_background);
        listView.setOnItemClickListener(this);
        datas = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            datas.add((10000 + i) + "");
        }
        listView.setAdapter(new MyAdapter());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String string = datas.get(position);
        et_input.setText(string);
        popupWindow.dismiss();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(parent.getContext(), R.layout.item_number, null);
            }
            else {
                view = convertView;
            }
            TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
            tv_number.setText(datas.get(position));

            view.findViewById(R.id.ib_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("hehe", "删删");
                    datas.remove(position);
                    notifyDataSetChanged();
                    if (datas.size() == 0) {
                        popupWindow.dismiss();
                    }
                }
            });

            return view;
        }
    }
}
