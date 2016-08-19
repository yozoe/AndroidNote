package com.example.test.net;

/**
 * Created by wangdong on 16/8/19.
 */
public class JsonRequest extends Request {

    public JsonRequest(HttpMethod mHttpMethod, String mUrl, RequestListener mRequestListener) {
        super(mHttpMethod, mUrl, mRequestListener);
    }

    @Override
    public Object paresResponse(Response response) {
        return null;
    }
}
