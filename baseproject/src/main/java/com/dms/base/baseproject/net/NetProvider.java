package com.dms.base.baseproject.net;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by wanglei on 2016/12/24.
 */

public interface NetProvider {
    long configConnectTimeoutSeconds();

    long configReadTimeoutSeconds();

    long configWriteTimeoutSeconds();

    CookieJar configCookie();

    boolean dispatchProgressEnable();

    Interceptor[] configInterceptors();

    void configHttps(OkHttpClient.Builder builder);

    boolean configLogEnable();


}
