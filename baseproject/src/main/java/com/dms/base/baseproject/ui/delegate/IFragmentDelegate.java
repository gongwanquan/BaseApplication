package com.dms.base.baseproject.ui.delegate;

import com.dms.base.baseproject.mvp.presenter.IPresenter;

public interface IFragmentDelegate<P extends IPresenter> extends IActivityDelegate<P> {
    void onLazyLoad();
}
