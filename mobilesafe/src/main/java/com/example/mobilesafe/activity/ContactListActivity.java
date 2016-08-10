package com.example.mobilesafe.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobilesafe.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangdong on 16/8/8.
 */
public class ContactListActivity extends Activity {
    private static final String TAG = "ContactListActivity";
    private ListView lv_contact;
    private List<HashMap<String, String>> contactList = new ArrayList<>();
    private MyAdapter mAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //8.填充数据适配器
            mAdapter = new MyAdapter();
            lv_contact.setAdapter(mAdapter);
        }
    };

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public HashMap<String, String> getItem(int i) {
            return contactList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view =  View.inflate(ContactListActivity.this, R.layout.listview_contact_item, null);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            tv_name.setText(getItem(i).get("name"));
            tv_phone.setText(getItem(i).get("phone"));
            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_list);

        initUI();

        if (Build.VERSION.SDK_INT >= 23) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.READ_CONTACTS}, 0);
            }
            else {
                initData();
            }
        }

    }

    private void initData() {

        //因为读取系统联系人可能是一个耗时操作,放置到子线程种处理
        new Thread() {
            public void run() {
                //1.获取内容解析器对象
                ContentResolver contentResolver = getContentResolver();
                //2.查询系统联系人数据库表过程(读取联系人权限)
                Cursor cursor = contentResolver.query(
                        Uri.parse("content://com.android.contacts/raw_contacts"),
                        new String[]{"contact_id"},
                        null, null, null);
                contactList.clear();
                //3.循环游标,直到没有数据位置
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    Log.i(TAG, "id = " + id);

                    //根据用户唯一性id值,查询data表和mimetype表生成的视图,获取data以及mimetype字段
                    Cursor indexCursor = contentResolver.query(
                            Uri.parse("content://com.android.contacts/data"),
                            new String[]{"data1", "mimetype"},
                            "raw_contact_id = ?", new String[]{id}, null);

                    HashMap<String, String> hashMap = new HashMap<String, String>();

                    //5.循环获取每一个联系人的电话号码以及姓名,数据类型
                    while (indexCursor.moveToNext()) {
                        String data = indexCursor.getString(0);
                        String type = indexCursor.getString(1);

                        //6区分类型去给hashMap填充
                        if (type.equals("vnd.android.cursor.item/phone_v2")) {
                            if (!TextUtils.isEmpty(data)) {
                                hashMap.put("phone", data);
                            }
                        }
                        else if (type.equals("vnd.android.cursor.item/name")) {
                            if (!TextUtils.isEmpty(type)) {
                                hashMap.put("name", data);
                            }
                        }

                    }
                    contactList.add(hashMap);
                    indexCursor.close();
                }
                cursor.close();
                //7.消息机制
                mHandler.sendEmptyMessage(0);
            }
        }.start();
    }

    private void initUI() {
        lv_contact = (ListView) findViewById(R.id.lv_contact);

        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //1.获取点中条目索引指向集合中的对象
                if (mAdapter != null) {
                    HashMap<String, String> hashMap = mAdapter.getItem(i);
                    //2.获取当前条目指向集合对应的电话号码
                    String phone = hashMap.get("phone");
                    //3.此电话号码需要给第三个导航界面使用

                    //4.在结束此界面回到第一个导航界面的时候,将数据返回
                    Intent intent = new Intent();
                    intent.putExtra("phone", phone);
                    setResult(0, intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0){
            if (permissions[0].equals(Manifest.permission.READ_CONTACTS)
                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // 用户同意
                initData();
            } else {
                // 用户不同意，向用户展示该权限作用
//                if (!shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
//                    showPermissionDialog();
//                    return;
//                }

            }
        }
    }
}
