package com.dms.base.baseproject.net;


import android.text.TextUtils;

import com.dms.base.baseproject.net.interceptor.LogInterceptor;
import com.dms.base.baseproject.net.progress.ProgressHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private NetProvider mDefaultProvider;

    private int mDefConnectTimeout = 10, mDefWriteTimeout = 10, mDefReadTimeout = 30;

    private Map<String, NetProvider> mProviderMap = new HashMap<>();

    private Map<String, Retrofit> mRetrofitMap = new HashMap<>();

    private Map<String, OkHttpClient> mClientMap = new HashMap<>();

    private ApiClient() {

    }

    private static class InstanceHolder {
        private static final ApiClient INSTANCE = new ApiClient();
    }

    public static ApiClient getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static void registerProvider(NetProvider provider) {
        getInstance().mDefaultProvider = provider;
    }

    public static void registerProvider(String baseUrl, NetProvider provider) {
        getInstance().mProviderMap.put(baseUrl, provider);
    }


    public Retrofit getRetrofit(String baseUrl, NetProvider provider) {
        if (HttpUrl.parse(baseUrl) == null) {
            throw new IllegalStateException("baseUrl can not be null");
        }

        if (null != mRetrofitMap.get(baseUrl)) {
            return mRetrofitMap.get(baseUrl);
        }

        if (null == provider) {
            provider = mProviderMap.get(baseUrl);
            if (null == provider) {
                provider = mDefaultProvider;
            }
        }

        if (provider == null) {
            throw new IllegalStateException("must register provider first");
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getClient(baseUrl, provider))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofit = builder.build();
        mRetrofitMap.put(baseUrl, retrofit);
        mProviderMap.put(baseUrl, provider);

        return retrofit;
    }

    public OkHttpClient getClient(String baseUrl, NetProvider provider) {
        if (HttpUrl.parse(baseUrl) == null) {
            throw new IllegalStateException("baseUrl can not be null");
        }
        if (null != mClientMap.get(baseUrl)) {
            return mClientMap.get(baseUrl);
        }

        if (provider == null) {
            throw new IllegalStateException("must register provider first");
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(
                provider.configConnectTimeoutSeconds() != 0
                        ? provider.configConnectTimeoutSeconds()
                        : mDefConnectTimeout, TimeUnit.SECONDS);
        builder.writeTimeout(
                provider.configWriteTimeoutSeconds() != 0
                        ? provider.configWriteTimeoutSeconds()
                        : mDefWriteTimeout, TimeUnit.SECONDS);
        builder.readTimeout(
                provider.configReadTimeoutSeconds() != 0
                        ? provider.configReadTimeoutSeconds()
                        : mDefReadTimeout, TimeUnit.SECONDS
        );


        CookieJar cookieJar = provider.configCookie();
        if (cookieJar != null) {
            builder.cookieJar(cookieJar);
        }

        if (provider.dispatchProgressEnable()) {
            builder.addInterceptor(ProgressHelper.get().getInterceptor());
        }

        Interceptor[] interceptors = provider.configInterceptors();
        if (interceptors != null && interceptors.length != 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        provider.configHttps(builder);

        if (provider.configLogEnable()) {
            LogInterceptor logInterceptor = new LogInterceptor();
            builder.addInterceptor(logInterceptor);
        }

        OkHttpClient client = builder.build();
        mClientMap.put(baseUrl, client);

        return client;
    }

}
