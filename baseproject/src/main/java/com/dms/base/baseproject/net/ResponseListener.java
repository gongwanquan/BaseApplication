package com.dms.base.baseproject.net;

public interface ResponseListener<T> {
    void onSuccess(T t);

    void onFailed(int code, String msg);

    void onError(Throwable throwable);
}
