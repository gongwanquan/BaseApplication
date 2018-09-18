package com.dms.base.baseproject.net.model;

public interface IModel<T> {

    boolean isNull();

    boolean isAuthError();

    boolean isBizError();

    int getCode();

    String getMsg();

    T getData();
}
