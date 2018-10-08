package com.dms.base.baseproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.blankj.utilcode.util.ToastUtils;
import com.dms.base.baseproject.mvp.provider.PresenterProvider;
import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.mvp.view.IView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IView {

    private Unbinder mUnBinder;

    protected P mPresenter;

    @Override
    public P createPresenter() {
        return PresenterProvider.createPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    public void showMessage(CharSequence charSequence) {
        ToastUtils.showShort(charSequence);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            mUnBinder = ButterKnife.bind(this);
        }

        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }

        initView(savedInstanceState);
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
