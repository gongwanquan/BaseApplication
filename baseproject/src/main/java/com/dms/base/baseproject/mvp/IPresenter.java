package com.dms.base.baseproject.mvp;

import com.dms.base.baseproject.net.model.IModel;
import com.dms.base.baseproject.net.ResponseListener;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();

    V getView();

    public <T extends IModel> void subscribe(Observable<T> observable, final ResponseListener<T> responseListener);
}
