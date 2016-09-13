package com.example.test.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangdong on 16/9/9.
 */
public class RecyclerViewTestActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setAdapter(mAdapter = new RecyclerAdapter());
        mAdapter = new RecyclerAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                Log.i("hehe", position + " ");
            }
        });

        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onLongClick(View parent, int position) {
                Log.i("hehe", position + " ");
                return false;
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    public interface OnItemClickListener {
        public void onClick(View parent, int position);
    }

    public interface OnItemLongClickListener {
        public boolean onLongClick(View parent, int position);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

        private List<String> datas;
        private LayoutInflater inflater;
        private OnItemClickListener onItemClickListener;
        private OnItemLongClickListener onItemLongListener;

        public RecyclerAdapter(Context context, List<String> datas) {
            Log.d("hehe", "你妹妹");
            this.datas = datas;
            inflater = LayoutInflater.from(context);
        }

        public void setOnClickListener(OnItemClickListener l) {
            this.onItemClickListener = l;
        }

        public void setOnItemLongClickListener(OnItemLongClickListener l) {
            this.onItemLongListener = l;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d("hehe", "on create view holder");
            MyViewHolder holder =
                    new MyViewHolder(inflater.inflate(R.layout.layout_recycler_test_view, parent, false));

            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            Log.d("hehe", "on bind view holder");

            if (onItemClickListener != null) {
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onClick(holder.itemView, pos);
                    }
                });
            }

            if (onItemLongListener != null) {
                holder.title.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemLongListener.onLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }

            holder.title.setText(mDatas.get(position));


            int height = Math.abs(new Random().nextInt() % 300);
            if (height < 200) {
                height += 200;
            }

            Log.i("hehe", "height = " + height + " ");

            holder.title.setHeight(height);
        }

        @Override
        public int getItemCount() {
            Log.i("hehe", "get item count");
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView title;

            public MyViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.id_num);
            }
        }

    }

}
