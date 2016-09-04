package com.example.test.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test.R;

/**
 * Created by Administrator on 2016/9/3.
 */
public class NotificationTestActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification_test);
    }

    @Override
    public void onClick(View v) {

    }

    private void testNotification() {
        Notification notification = new Notification();
//        notification.icon = R.mipmap.ic_launcher;
//        notification.tickerText = "hello world";
//        notification.when = System.currentTimeMillis();
//        notification.flags = Notification.FLAG_AUTO_CANCEL;

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(pendingIntent).setSmallIcon(R.mipmap.ic_launcher).setWhen(System.currentTimeMillis()).setAutoCancel(false).setContentTitle("hello world").setContentText("hello world");
//        startForeground(XXX, builder.getNotification());

    }
}
