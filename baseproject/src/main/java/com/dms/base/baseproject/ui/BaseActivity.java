package com.dms.base.baseproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.dms.base.baseproject.mvp.IPresenter;
import com.dms.base.baseproject.mvp.IView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


import butterknife.ButterKnife;


public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IView<P> {
    protected P mPresenter;

    @Override
    public abstract P createPresenter();

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    protected void initView() {
    }

    protected void initData() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
        if(getLayoutId() > 0) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

}
