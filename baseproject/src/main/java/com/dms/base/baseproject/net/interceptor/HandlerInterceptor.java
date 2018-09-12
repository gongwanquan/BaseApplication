package com.dms.base.baseproject.net.interceptor;

import com.dms.base.baseproject.net.RequestHandler;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HandlerInterceptor implements Interceptor {
    RequestHandler handler;

    public HandlerInterceptor(RequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (handler != null) {
            request = handler.onBeforeRequest(request, chain);
        }
        Response response = chain.proceed(request);
        if (handler != null) {
            Response tmp = handler.onAfterRequest(response, chain);
            if (tmp != null) {
                return tmp;
            }

        }
        return response;
    }
}
