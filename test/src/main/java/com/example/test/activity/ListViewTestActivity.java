package com.example.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdong on 16/9/18.
 */
public class ListViewTestActivity extends Activity {

    private ListView listView;
    private List<String> data;
    private ViewHolderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("hehe", id + "");
            }
        });

        data = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }

        adapter = new ViewHolderAdapter();
        listView.setAdapter(adapter);
    }

    public class ViewHolderAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position + 1000;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(ListViewTestActivity.this).inflate(R.layout.viewholder_item, null);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(data.get(position));

            return convertView;
        }

        public final class ViewHolder {
            public ImageView imageView;
            public TextView textView;
        }
    }

    public void handleButtonAction(View v) {
        data.add("new");
        adapter.notifyDataSetChanged();
        listView.setSelection(data.size() - 1);
    }
}
