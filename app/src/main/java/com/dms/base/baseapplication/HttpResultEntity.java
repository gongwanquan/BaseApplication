package com.dms.base.baseapplication;

import com.dms.base.baseproject.net.IBaseModel;

import java.util.Collection;
import java.util.Map;

/**
 * Created by 龚万全 on 2016/9/27.
 * Description:
 */

public class HttpResultEntity<T> implements IBaseModel<T> {

    /**
     * msg : success
     * retCode : 200
     */

    private String msg;

    private int retCode;

    private T result;

    @Override
    public boolean isError() {
        return 200 != retCode;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isNull() {
        return null == result
                || (result instanceof Collection && ((Collection) result).isEmpty())
                || (result instanceof Map && ((Map) result).isEmpty());
    }

    @Override
    public int getCode() {
        return retCode;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public T getData() {
        return result;
    }



}
