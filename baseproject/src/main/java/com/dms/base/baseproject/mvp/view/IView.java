package com.dms.base.baseproject.mvp.view;


import com.dms.base.baseproject.net.error.NetError;
import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IView {
    void showLoading();

    void hideLoading();

    LifecycleTransformer bindLifecycle();

    void showMessage(CharSequence charSequence);

    void showError(NetError netError);
}
