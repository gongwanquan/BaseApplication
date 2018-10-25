package com.dms.base.baseapplication.model;

import com.dms.base.baseproject.net.model.IModel;

public class DmsResp<T> implements IModel {
    boolean success;

    String message;

    T data;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return false;
    }

    @Override
    public boolean isNull() {
        return null == data;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return message;
    }

    @Override
    public Object getData() {
        return data;
    }
}
