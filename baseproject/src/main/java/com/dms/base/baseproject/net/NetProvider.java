package com.dms.base.baseproject.net;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by wanglei on 2016/12/24.
 */

public interface NetProvider {
    String configBaseUrl();

    long configConnectTimeoutSeconds();

    long configReadTimeoutSeconds();

    long configWriteTimeoutSeconds();

    CookieJar configCookie();

    boolean configMultiUrlEnable();

    boolean configProgressEnable();

    HttpLoggingInterceptor.Logger configLogEnable();

    Interceptor[] configInterceptors();

    void configHttps(OkHttpClient.Builder builder);
}
