package com.dms.base.baseproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.ui.widget.TitleBar;
import com.dms.base.baseproject.ui.widget.state_layout.StateLayout;
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
    public void setContentView(View view) {
        mStateView = new StateLayout(this);
        mStateView.addView(view);
        View.OnClickListener emptyAndErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorAndEmptyAction();
            }
        };
        mStateView.setEmptyAction(emptyAndErrorClickListener);
        mStateView.setErrorAction(emptyAndErrorClickListener);

        ViewGroup contentParent = getWindow().getDecorView().findViewById(android.R.id.content);
        contentParent.addView(mStateView);
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
