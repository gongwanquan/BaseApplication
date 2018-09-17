package com.dms.base.baseapplication;

import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.ui.BaseFragment;

public abstract class BaseUiFragment<P extends IPresenter> extends BaseFragment<P> {
    private LoadingDialog mLoadingDialog;

    @Override
    public void showLoading() {
        if(null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(getActivity());
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
