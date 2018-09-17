package com.dms.base.baseproject.mvp;

import com.dms.base.baseproject.net.IBaseModel;
import com.dms.base.baseproject.net.ResponseListener;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();

    V getView();

    public void subscribe(Observable observable, Consumer consumer);

    public <T extends IBaseModel> void subscribe(Observable<T> observable, final ResponseListener<T> responseListener);
}
