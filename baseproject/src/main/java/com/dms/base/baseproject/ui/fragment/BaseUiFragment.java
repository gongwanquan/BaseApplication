package com.dms.base.baseproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.ui.widget.state_layout.StateLayout;
import com.dms.base.baseproject.net.error.NetError;

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
                mStateView.showError(0, "数据解析错误", "请检查数据格式");
                break;
            case NetError.CONNECT_ERROR:
                mStateView.showError(0, "网络连接失败", "请检查网络连接");
                break;
            case NetError.AUTH_ERROR:
                mStateView.showError(0, null, netError.getMessage());
                break;
            case NetError.NO_DATA_ERROR:
                mStateView.showEmpty();
                break;
            case NetError.BUSINESS_ERROR:
                mStateView.showError(0, null, netError.getMessage());
                break;
            case NetError.OTHER_ERROR:
                showMessage(netError.getMessage());
                break;
        }
    }
}
