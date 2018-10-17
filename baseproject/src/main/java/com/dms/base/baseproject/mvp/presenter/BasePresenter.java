package com.dms.base.baseproject.mvp.presenter;

import com.dms.base.baseproject.mvp.view.IView;
import com.dms.base.baseproject.net.ApiObserver;
import com.dms.base.baseproject.net.transformer.ApiTransformer;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.net.model.IModel;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.transformer.SchedulerTransformer;
import java.lang.ref.WeakReference;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> mView;

    private CompositeDisposable mCompositeDisposable;


    @Override
    public void attachView(IView view) {
        mView = new WeakReference(view);
    }

    @Override
    public void detachView() {
        if(null != mCompositeDisposable && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }

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

    public void addDisposable(Disposable disposable) {
        if(null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public <T extends IModel> void subscribe(Observable<T> observable, final ResponseListener<T> responseListener) {
        getView().showLoading();

        observable.compose(new SchedulerTransformer<T>())
                .compose(new ApiTransformer<T>())
                .subscribe(new ApiObserver<T>() {
                    @Override
                    public void onSuccess(T t) {
                        responseListener.onSuccess(t);
                    }

                    @Override
                    public void onFailed(NetError netError) {
                        getView().hideLoading();
                        if (!responseListener.handleError(netError)) {
                            getView().showError(netError);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }

}
