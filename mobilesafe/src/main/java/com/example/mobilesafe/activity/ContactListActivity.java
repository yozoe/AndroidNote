package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.mobilesafe.R;

/**
 * Created by wangdong on 16/8/8.
 */
public class ContactListActivity extends Activity {
    private static final String TAG = "ContactListActivity";
    private ListView lv_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_list);

        initUI();
        initData();
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
                //3.循环游标,直到没有数据位置
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    Log.i(TAG, "id = " + id);

                    //根据用户唯一性id值,查询data表和mimetype表生成的视图,获取data以及mimetype字段
                    Cursor indexCursor = contentResolver.query(
                            Uri.parse("content://com.android.contacts/data"),
                            new String[]{"data1", "mimetype"},
                            "raw_contact_id = ?", new String[]{id}, null);

                    //5.循环获取每一个联系人的电话号码以及姓名,数据类型
                    while (indexCursor.moveToNext()) {
                        String a = indexCursor.getString(0);
                        String b = indexCursor.getString(1);
                        Log.i(TAG, "data = " + a + " mimetype = " + b);

                    }

                    indexCursor.close();
                }
                cursor.close();
            }
        };



    }

    private void initUI() {
        lv_contact = (ListView) findViewById(R.id.lv_contact);
    }
}
