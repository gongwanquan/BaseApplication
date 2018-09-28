package com.dms.base.baseproject.mvp.presenter;

import com.dms.base.baseproject.mvp.view.IView;

public interface IPresenter<V extends IView> {
    void attachView(V v);

    void detachView();

    V getView();
}
