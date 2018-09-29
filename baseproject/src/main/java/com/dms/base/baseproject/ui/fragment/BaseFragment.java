package com.dms.base.baseproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import com.blankj.utilcode.util.ToastUtils;
import com.dms.base.baseproject.mvp.factory.PresenterFactory;
import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.mvp.view.IView;
import com.dms.base.baseproject.ui.delegate.IFragmentDelegate;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IFragmentDelegate<P>, IView {
    private View mRootView;

    private Unbinder mUnBinder;

    protected P mPresenter;

    private boolean mIsViewInitiated, mIsVisibleToUser, mIsDataInitiated;

    @Override
    public P createPresenter() {
        return PresenterFactory.createPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    @Override
    public void showMessage(CharSequence charSequence) {
        ToastUtils.showShort(charSequence);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (null == mRootView && getLayoutId() > 0) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            mUnBinder = ButterKnife.bind(this, mRootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mRootView);
            }
        }
        initView(savedInstanceState);
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

        mIsViewInitiated = true;
        prepareLoadData(false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        prepareLoadData(false);
    }

    public boolean prepareLoadData(boolean forceLoad) {
        if (mIsViewInitiated && mIsVisibleToUser && (!mIsDataInitiated || forceLoad)) {
            mIsDataInitiated = true;
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

        if (null != mUnBinder && mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
    }
}