package com.example.test.net;

/**
 * Created by wangdong on 16/8/22.
 */
public interface HttpStack {
    public Response performRequest(Request<?> request);
}
