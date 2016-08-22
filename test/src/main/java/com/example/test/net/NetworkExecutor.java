package com.example.test.net;

import java.util.concurrent.BlockingQueue;

/**
 * Created by wangdong on 16/8/22.
 */
public class NetworkExecutor extends Thread {
    private BlockingQueue<Request<?>> mRequestQueue;
    private HttpStack mHttpStack;
}
