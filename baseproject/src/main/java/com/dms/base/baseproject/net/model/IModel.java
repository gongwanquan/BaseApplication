package com.dms.base.baseproject.net.model;

public interface IModel<T> {
    boolean isSuccess();

    boolean isAuthError();

    boolean isBizError();

    boolean isNull();

    int getCode();

    String getMsg();

    T getData();
}
