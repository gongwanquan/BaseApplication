package com.dms.base.baseapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.dms.base.baseapplication.ui.widget.TitleBar;
import com.dms.base.baseapplication.ui.widget.state_layout.StateLayout;
import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.BaseActivity;

public abstract class BaseUIActivity<P extends IPresenter> extends BaseActivity<P> {
    protected TitleBar mTitleBar;

    private StateLayout mStateView;

    protected void onErrorAndEmptyAction() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mTitleBar = new TitleBar(this);
        mTitleBar.attachActivity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        mStateView = new StateLayout(this);
        mStateView.setErrorAndEmptyAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorAndEmptyAction();
            }
        });
        setContentView(mStateView);
        LayoutInflater.from(this).inflate(layoutResID, mStateView, true);
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
