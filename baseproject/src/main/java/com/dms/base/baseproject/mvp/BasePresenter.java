package com.dms.base.baseproject.mvp;

import com.blankj.utilcode.util.ToastUtils;
import com.dms.base.baseproject.net.IBaseModel;
import com.dms.base.baseproject.net.ResponseListener;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class BasePresenter<V extends IView> implements IPresenter<V> {
    private WeakReference<V> mView;

    @Override
    public void attachView(V view) {
        mView = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (mView.get() != null) {
            mView.clear();
        }
        mView = null;
    }

    @Override
    public V getView() {
        if (mView == null || mView.get() == null) {
            throw new IllegalStateException("view can not be null");
        }
        return mView.get();
    }

    @Override
    public void subscribe(Observable observable, Consumer consumer) {
        observable.compose(getView().bindLifecycle())
                .subscribe(consumer);
    }

    @Override
    public <T extends IBaseModel> void subscribe(Observable<T> observable, final ResponseListener<T> responseListener) {
        getView().showLoading();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getView().bindLifecycle())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T result) throws Exception {
                        if(result.isError()) {
                            ToastUtils.showShort(result.getMsg());
                            responseListener.onFailed(result.getCode(), result.getMsg());
                        } else {
                            responseListener.onSuccess(result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        responseListener.onError(throwable);
                        getView().hideLoading();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        getView().hideLoading();
                    }
                });
    }

}
