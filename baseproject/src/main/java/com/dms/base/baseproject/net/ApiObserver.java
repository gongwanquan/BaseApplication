package com.dms.base.baseproject.net;

import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.net.model.IModel;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import io.reactivex.Observer;

public abstract class ApiObserver<T extends IModel> implements Observer<T> {

    @Override
    public void onNext(T result) {
        onSuccess(result);
    }

    @Override
    public void onError(Throwable throwable) {
        NetError netError = null;
        if (throwable instanceof NetError) {
            netError = (NetError) throwable;
        } else if (throwable instanceof JSONException
                || throwable instanceof JsonParseException
                || throwable instanceof JsonSyntaxException) {
            netError = new NetError(throwable, NetError.PARSE_ERROR);
        } else if (throwable instanceof SocketTimeoutException
                || throwable instanceof UnknownHostException) {
            netError = new NetError(throwable, NetError.CONNECT_ERROR);
        } else {
            netError = new NetError(throwable, NetError.OTHER_ERROR);
        }

        onFailed(netError);
    }

    public abstract void onSuccess(T t);

    public abstract void onFailed(NetError netError);
}
