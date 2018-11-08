package com.dms.base.baseapplication;

import android.app.Application;
import com.blankj.utilcode.util.LogUtils;
import com.dms.base.baseapplication.net.KeyInterceptor;
import com.dms.base.baseapplication.net.MyLogger;
import com.dms.base.baseproject.net.ApiClient;
import com.dms.base.baseproject.net.NetProvider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class APP extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
        LogUtils.getConfig().setGlobalTag("GWQ_LOG");

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
            public boolean configMultiUrlEnable() {
                return false;
            }

            @Override
            public boolean configProgressEnable() {
                return true;
            }

            @Override
            public HttpLoggingInterceptor.Logger configLogEnable() {
                return new MyLogger();
            }


            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[]{new KeyInterceptor()};
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }
        });

    }



}

