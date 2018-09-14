package com.dms.base.baseproject.net;

public interface IBaseModel<T> {
    boolean isError();

    boolean isAuthError();

    boolean isNull();

    int getCode();

    String getMsg();

    T getData();
}
