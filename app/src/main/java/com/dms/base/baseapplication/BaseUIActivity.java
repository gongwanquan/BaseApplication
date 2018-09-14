package com.dms.base.baseapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.ui.BaseActivity;

public abstract class BaseUIActivity<P extends IPresenter> extends BaseActivity<P> {
    protected TitleBar mTitleBar;

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mTitleBar = new TitleBar(this);
        mTitleBar.attachActivity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showLoading() {
        if(null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if(null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}
