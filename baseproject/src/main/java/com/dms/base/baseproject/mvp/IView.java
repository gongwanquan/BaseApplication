package com.dms.base.baseproject.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IView<P extends IPresenter> {
    void showLoading();

    void hideLoading();

    int getLayoutId();

    LifecycleTransformer bindLifecycle();

    P createPresenter();
}
