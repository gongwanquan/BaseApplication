package com.dms.base.baseproject.ui.delegate;

import android.os.Bundle;

import com.dms.base.baseproject.mvp.presenter.IPresenter;

public interface IActivityDelegate<P extends IPresenter> {

    P createPresenter();

    int getLayoutId();

    void initView(Bundle savedInstanceState);

    void initData(Bundle savedInstanceState);
}
