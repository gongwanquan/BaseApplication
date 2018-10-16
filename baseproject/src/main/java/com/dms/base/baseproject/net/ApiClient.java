package com.dms.base.baseproject.net;


import com.dms.base.baseproject.net.interceptor.LogInterceptor;
import com.dms.base.baseproject.net.progress.ProgressHelper;
import java.util.concurrent.TimeUnit;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private int mDefConnectTimeout = 10, mDefWriteTimeout = 10, mDefReadTimeout = 30;

    private NetProvider mNetProvider;

    private Retrofit mRetrofit;

    private OkHttpClient mHttpClient;

    private ApiClient() {

    }

    private static class InstanceHolder {
        private static final ApiClient INSTANCE = new ApiClient();
    }

    public static ApiClient getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static void registerProvider(NetProvider provider) {
        getInstance().mNetProvider = provider;
    }

    public Retrofit getRetrofit() {
        if (null == mRetrofit) {
            if (null == mNetProvider) {
                throw new IllegalStateException("must register provider first");
            }

            String baseUrl = mNetProvider.configBaseUrl();
            if (HttpUrl.parse(baseUrl) == null) {
                throw new IllegalStateException("baseUrl can not be null");
            }

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

            mRetrofit = builder.build();

        }

        return mRetrofit;
    }

    public OkHttpClient getHttpClient() {

        if (null == mHttpClient) {
            if (null == mNetProvider) {
                throw new IllegalStateException("must register provider first");
            }

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.connectTimeout(
                    mNetProvider.configConnectTimeoutSeconds() != 0
                            ? mNetProvider.configConnectTimeoutSeconds()
                            : mDefConnectTimeout, TimeUnit.SECONDS);
            builder.writeTimeout(
                    mNetProvider.configWriteTimeoutSeconds() != 0
                            ? mNetProvider.configWriteTimeoutSeconds()
                            : mDefWriteTimeout, TimeUnit.SECONDS);
            builder.readTimeout(
                    mNetProvider.configReadTimeoutSeconds() != 0
                            ? mNetProvider.configReadTimeoutSeconds()
                            : mDefReadTimeout, TimeUnit.SECONDS
            );


            CookieJar cookieJar = mNetProvider.configCookie();
            if (cookieJar != null) {
                builder.cookieJar(cookieJar);
            }

            if (mNetProvider.dispatchProgressEnable()) {
                builder.addInterceptor(ProgressHelper.get().getInterceptor());
            }

            Interceptor[] interceptors = mNetProvider.configInterceptors();
            if (interceptors != null && interceptors.length != 0) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }

            mNetProvider.configHttps(builder);

            if (mNetProvider.configLogEnable()) {
                LogInterceptor logInterceptor = new LogInterceptor();
                builder.addInterceptor(logInterceptor);
            }

            mHttpClient = builder.build();
        }

        return mHttpClient;
    }

}
