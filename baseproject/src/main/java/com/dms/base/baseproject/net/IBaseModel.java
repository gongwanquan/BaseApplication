package com.dms.base.baseproject.net;

public interface IBaseModel<T> {
    boolean isError();

    boolean isNull();

    int getCode();

    String getMsg();

    T getData();
}
