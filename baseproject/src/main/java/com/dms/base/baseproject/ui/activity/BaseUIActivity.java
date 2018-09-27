package com.dms.base.baseproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.dms.base.baseproject.ui.widget.TitleBar;
import com.dms.base.baseproject.ui.widget.state_layout.StateLayout;
import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.net.error.NetError;

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
        mStateView.setContentResource(layoutResID);
        setContentView(mStateView);
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
