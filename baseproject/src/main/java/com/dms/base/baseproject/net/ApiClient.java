package com.dms.base.baseproject.net;


import java.util.concurrent.TimeUnit;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
                            : mDefReadTimeout, TimeUnit.SECONDS);

            CookieJar cookieJar = mNetProvider.configCookie();
            if (cookieJar != null) {
                builder.cookieJar(cookieJar);
            }

            if (mNetProvider.configProgressEnable()) {
                ProgressManager.getInstance().with(builder);
            }

            if(mNetProvider.configMultiUrlEnable()) {
                RetrofitUrlManager.getInstance().with(builder);
            }

            if (mNetProvider.configLogEnable() != null) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(mNetProvider.configLogEnable());
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }

            Interceptor[] interceptors = mNetProvider.configInterceptors();
            if (interceptors != null && interceptors.length != 0) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }

            mNetProvider.configHttps(builder);

            mHttpClient = builder.build();
        }

        return mHttpClient;
    }

}
