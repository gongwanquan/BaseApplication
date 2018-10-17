package com.dms.base.baseproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.rxbus.RxBus;
import com.blankj.utilcode.util.ToastUtils;
import com.dms.base.baseproject.mvp.provider.PresenterProvider;
import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.mvp.view.IView;
import com.dms.base.baseproject.permission.IPermissionCallback;
import com.tbruyelle.rxpermissions2.RxPermissions;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;


public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    private Unbinder mUnBinder;

    protected P mPresenter;

    private RxPermissions mRxPermissions;

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

    @Override
    public void requestPermission(String permission, final IPermissionCallback callback) {
        if(null == mRxPermissions) {
            mRxPermissions = new RxPermissions(this);
        }

        mRxPermissions.request(permission)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        callback.onPermissionResult(aBoolean);
                    }
                });
    }
}
