package com.dms.base.baseapplication;

import android.app.Application;
import com.dms.base.baseapplication.net.KeyInterceptor;
import com.dms.base.baseproject.net.ApiClient;
import com.dms.base.baseproject.net.NetProvider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.registerProvider(new NetProvider() {
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
                return false;
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
