package com.example.test.net;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangdong on 16/8/19.
 */
public class RequestQueue {

    private BlockingQueue<Request<?>> mRequestQueue = new PriorityBlockingQueue<>();
    private AtomicInteger mSerialNumGenerator = new AtomicInteger(0);
    public static int DEFAULT_CORE_NUMS = Runtime.getRuntime().availableProcessors() + 1;
    private int mDispatcherNums = DEFAULT_CORE_NUMS;

}
