package com.example.test.net;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by wangdong on 16/8/22.
 */
public class ResponseDelivery implements Executor {

    Handler mResponseHandler = new Handler(Looper.getMainLooper());

    public void deliveryResponse(final Request<?> request, final Response response) {

    }

    @Override
    public void execute(Runnable command) {

    }
}
