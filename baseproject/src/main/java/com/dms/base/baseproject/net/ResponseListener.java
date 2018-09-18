package com.dms.base.baseproject.net;

import com.dms.base.baseproject.net.error.NetError;

public interface ResponseListener<T> {
    void onSuccess(T t);

    boolean handleError(NetError netError);
}
