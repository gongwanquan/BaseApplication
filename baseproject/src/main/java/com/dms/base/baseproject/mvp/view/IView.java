package com.dms.base.baseproject.mvp.view;


import android.os.Bundle;

import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.net.error.NetError;

public interface IView {

    int getLayoutId();

    <P extends IPresenter> P createPresenter();

    void initView(Bundle savedInstanceState);

    void initData(Bundle savedInstanceState);

    void showLoading();

    void hideLoading();

    void showMessage(CharSequence charSequence);

    void showError(NetError netError);
}
