package com.dms.base.baseapplication.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 龚万全 on 2016/9/28.
 * Description:
 */

public class KeyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl httpUrl = request.url().newBuilder().addQueryParameter("key", "1782b51bf7718").build();

        return chain.proceed(request.newBuilder().url(httpUrl).build());
    }
}
