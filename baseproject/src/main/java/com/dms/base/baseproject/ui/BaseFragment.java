package com.dms.base.baseproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.mvp.IView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends IPresenter> extends com.trello.rxlifecycle2.components.support.RxFragment implements IView {
    private View mRootView;

    protected P mPresenter;

    protected boolean mIsViewInitiated;

    protected boolean mIsVisibleToUser;

    protected boolean mIsDataInitiated;

    @Override
    public abstract P createPresenter();

    protected void initView() {
    }

    protected void initData() {
    }

    protected void onLazyLoad() {

    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (null == mRootView && getLayoutId() > 0) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mRootView);
            }
        }
        initView();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewInitiated = true;
        prepareLoadData(false);

        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }

        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        prepareLoadData(false);
    }

    public boolean prepareLoadData(boolean forceLoad) {
        if (mIsViewInitiated && mIsVisibleToUser && (!mIsDataInitiated || forceLoad)) {
            onLazyLoad();
            return true;
        }

        return false;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
