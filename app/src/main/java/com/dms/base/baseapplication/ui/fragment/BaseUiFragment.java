package com.dms.base.baseapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.base.baseapplication.ui.widget.state_layout.StateLayout;
import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.BaseFragment;

public abstract class BaseUiFragment<P extends IPresenter> extends BaseFragment<P> {

    private StateLayout mStateView;

    protected void onErrorAndEmptyAction() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mStateView = new StateLayout(getContext());
        mStateView.setErrorAndEmptyAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorAndEmptyAction();
            }
        });
        mStateView.addView(view);
        return mStateView;
    }

    @Override
    public void showLoading() {
        mStateView.showProgressView();
    }

    @Override
    public void hideLoading() {
        mStateView.showContentView();
    }

    @Override
    public void showError(NetError netError) {
        switch (netError.getErrorType()) {

            case NetError.PARSE_ERROR:

                break;
            case NetError.CONNECT_ERROR:
                mStateView.showErrorView();
                break;
            case NetError.AUTH_ERROR:

                break;
            case NetError.NO_DATA_ERROR:
                mStateView.showEmptyView();
                break;
            case NetError.BUSINESS_ERROR:

                break;
            case NetError.OTHER_ERROR:

                break;
        }
    }
}
