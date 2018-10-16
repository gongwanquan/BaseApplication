package com.dms.base.baseproject.net.transformer;

import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.net.model.IModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ApiTransformer<T extends IModel> implements ObservableTransformer<T, T> {


    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.flatMap(new Function<T, Observable<T>>() {
            @Override
            public Observable<T> apply(T t) throws Exception {
                if (t.isSuccess()) {
                    return Observable.just(t);
                } else {
                    if (t.isNull()) {
                        throw new NetError(t.getMsg(), NetError.NO_DATA_ERROR, t.getCode());
                    } else if (t.isAuthError()) {
                        throw new NetError(t.getMsg(), NetError.AUTH_ERROR, t.getCode());
                    } else if (t.isBizError()) {
                        throw new NetError(t.getMsg(), NetError.BUSINESS_ERROR, t.getCode());
                    } else {
                        throw new NetError(t.getMsg(), NetError.OTHER_ERROR, t.getCode());
                    }
                }
            }
        });
    }
}
