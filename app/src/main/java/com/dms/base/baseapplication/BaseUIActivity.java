package com.dms.base.baseapplication;

import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.ui.BaseActivity;

public abstract class BaseUIActivity<P extends IPresenter> extends BaseActivity<P> {
    private LoadingDialog mLoadingDialog;


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
