package com.dms.base.baseproject.mvp.presenter;

import com.dms.base.baseproject.mvp.view.IView;
import com.dms.base.baseproject.net.ApiObserver;
import com.dms.base.baseproject.net.ApiTransformer;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.net.model.IModel;
import com.dms.base.baseproject.net.ResponseListener;
import java.lang.ref.WeakReference;
import io.reactivex.Observable;


public class BasePresenter<V extends IView> implements IPresenter<V> {

    public BasePresenter() {
    }

    private WeakReference<V> mView;

    @Override
    public void attachView(IView view) {
        mView = new WeakReference(view);
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

    public <T extends IModel> void subscribe(Observable<T> observable, final ResponseListener<T> responseListener) {
        getView().showLoading();

        observable.compose(new ApiTransformer<T>())
                .compose(getView().bindLifecycle())
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
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }

}
