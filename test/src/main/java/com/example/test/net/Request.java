package com.example.test.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdong on 16/8/18.
 */
public abstract class Request<T> implements Comparable<Request<T>> {

    public enum HttpMethod {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        private String mHttpMethod = "";

        HttpMethod(String method) {
            mHttpMethod = method;
        }

        @Override
        public String toString() {
            return mHttpMethod;
        }
    }

    public enum Priority {
        LOW,
        NORMAL,
        HIGN,
        IMMEDIATE
    }

    public static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected int mSerialNum = 0;
    protected Priority mPriority = Priority.NORMAL;
    protected boolean isCancel = false;
    private boolean mShouldCache = true;
    protected RequestListener<T> mRequestListener;
    private String mUrl = "";
    HttpMethod mHttpMethod = HttpMethod.GET;
    private Map<String, String> mHeaders = new HashMap<>();
    private Map<String, String> mBodyParams = new HashMap<>();

    public Request(HttpMethod mHttpMethod, String mUrl, RequestListener<T> mRequestListener) {
        this.mHttpMethod = mHttpMethod;
        this.mUrl = mUrl;
        this.mRequestListener = mRequestListener;
    }

    public void addHeader(String name, String value) {
        mHeaders.put(name, value);
    }

    public abstract T paresResponse(Response response);

    public final void deliveryResponse(Response response) {

    }

    @Override
    public int compareTo(Request<T> another) {

        return 0;
    }

    public interface RequestListener<T> {
        public void onComplete(int stCode, T response, String erMsg);
    }
}
