package com.dms.base.baseapplication;

import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.net.error.NetError;
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

    @Override
    public void showError(NetError netError) {
        switch (netError.getErrorType()) {

            case NetError.PARSE_ERROR:

                break;
            case NetError.CONNECT_ERROR:

                break;
            case NetError.AUTH_ERROR:

                break;
            case NetError.NO_DATA_ERROR:

                break;
            case NetError.BUSINESS_ERROR:

                break;
            case NetError.OTHER_ERROR:

                break;
        }
    }
}
