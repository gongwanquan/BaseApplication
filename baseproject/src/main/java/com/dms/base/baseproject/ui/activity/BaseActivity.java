package com.dms.base.baseproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.blankj.utilcode.util.ToastUtils;
import com.dms.base.baseproject.mvp.provider.PresenterProvider;
import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.mvp.view.IView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {
    protected View mRootView;

    private Unbinder mUnBinder;

    protected P mPresenter;

    @Override
    public P createPresenter() {
        return PresenterProvider.createPresenter(this);
    }

    @Override
    public void initView(View rootView) {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void showMessage(CharSequence charSequence) {
        ToastUtils.showShort(charSequence);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() > 0) {
            ViewGroup contentParent = getWindow().getDecorView().findViewById(android.R.id.content);
            mRootView = LayoutInflater.from(this).inflate(getLayoutId(), contentParent, false);
            mUnBinder = ButterKnife.bind(this, mRootView);
            setContentView(mRootView);
        }

        initView(mRootView);

        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }

        initData(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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
