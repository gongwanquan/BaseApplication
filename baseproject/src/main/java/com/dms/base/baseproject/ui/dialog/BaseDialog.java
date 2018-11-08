package com.dms.base.baseproject.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.mvp.provider.PresenterProvider;
import com.dms.base.baseproject.mvp.view.IView;
import com.dms.base.baseproject.net.error.NetError;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialog<P extends IPresenter> extends DialogFragment implements IView {
    protected View mRootView;

    private Unbinder mUnBinder;

    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (null == mRootView && getLayoutId() > 0) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        } else {
            ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mRootView);
            }
        }
        mUnBinder = ButterKnife.bind(this, mRootView);
        initView(mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }

        initData(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter = null;
        }

        if (null != mUnBinder && mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
    }

    @Override
    public <P extends IPresenter> P createPresenter() {
        return PresenterProvider.createPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(CharSequence charSequence) {
        ToastUtils.showShort(charSequence);
    }

    @Override
    public void showError(NetError netError) {
        ToastUtils.showShort(netError.getMessage());
    }
}
