package com.dms.base.baseapplication;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.dms.base.baseapplication.net.KeyInterceptor;
import com.dms.base.baseproject.net.ApiClient;
import com.dms.base.baseproject.net.NetProvider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);

        ApiClient.registerProvider(new NetProvider() {
            @Override
            public String configBaseUrl() {
                return BaseConfig.BASE_URL;
            }

            @Override
            public long configConnectTimeoutSeconds() {
                return 0;
            }

            @Override
            public long configReadTimeoutSeconds() {
                return 0;
            }

            @Override
            public long configWriteTimeoutSeconds() {
                return 0;
            }

            @Override
            public okhttp3.CookieJar configCookie() {
                return null;
            }

            @Override
            public boolean dispatchProgressEnable() {
                return true;
            }

            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[]{new KeyInterceptor()};
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }
        });

    }
}
