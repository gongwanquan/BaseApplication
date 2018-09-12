package com.dms.base.baseapplication;

import com.dms.base.baseproject.mvp.BasePresenter;
import com.dms.base.baseproject.net.ApiClient;
import com.dms.base.baseproject.net.ResponseListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FuckPresenter extends BasePresenter<FuckView> {
    private ApiStore mApiStore;

    public FuckPresenter() {
        mApiStore = ApiClient.getInstance()
                .getRetrofit("http://apicloud.mob.com/", null)
                .create(ApiStore.class);
    }

    public void getHistory() {

        subscribe(mApiStore.getHistory("0911"), new ResponseListener<HttpResultEntity<List<HistoryEntity>>>() {
            @Override
            public void onSuccess(HttpResultEntity<List<HistoryEntity>> listHttpResultEntity) {
                if (!listHttpResultEntity.isNull()) {
                    getView().fuck(listHttpResultEntity.getData().get(0).getEvent());
                }
            }

            @Override
            public void onFailed(int code, String msg) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }


}
