package com.dms.base.baseproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.base.baseproject.ui.widget.state_layout.StateLayout;
import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.fragment.BaseFragment;

public abstract class BaseUiFragment<P extends IPresenter> extends BaseFragment<P> {

    private StateLayout mStateView;

    protected void onErrorAndEmptyAction() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mStateView = new StateLayout(getContext());
        mStateView.addView(view);
        return mStateView;
    }

    @Override
    public void showLoading() {
        mStateView.showLoading();
    }

    @Override
    public void hideLoading() {
        mStateView.showContent();
    }

    @Override
    public void showError(NetError netError) {
        switch (netError.getErrorType()) {

            case NetError.PARSE_ERROR:

                break;
            case NetError.CONNECT_ERROR:
                mStateView.showError();
                break;
            case NetError.AUTH_ERROR:

                break;
            case NetError.NO_DATA_ERROR:
                mStateView.showEmpty();
                break;
            case NetError.BUSINESS_ERROR:

                break;
            case NetError.OTHER_ERROR:

                break;
        }
    }
}
