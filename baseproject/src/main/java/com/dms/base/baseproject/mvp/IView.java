package com.dms.base.baseproject.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IView<P> {
    void showLoading();

    void hideLoading();

    int getLayoutId();

    LifecycleTransformer bindLifecycle();

    P createPresenter();
}
