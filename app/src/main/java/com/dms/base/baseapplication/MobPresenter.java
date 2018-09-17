package com.dms.base.baseapplication;

import com.dms.base.baseproject.mvp.BasePresenter;
import com.dms.base.baseproject.net.ApiClient;
import com.dms.base.baseproject.net.ResponseListener;

import java.util.List;

public class MobPresenter extends BasePresenter<MobView> {
    private ApiStore mApiStore;

    public MobPresenter() {
        mApiStore = ApiClient.getInstance()
                .getRetrofit("http://apicloud.mob.com/", null)
                .create(ApiStore.class);
    }

    public void getHistory(String date) {

        subscribe(mApiStore.getHistory(date), new ResponseListener<HttpResultEntity<List<HistoryEntity>>>() {
            @Override
            public void onSuccess(HttpResultEntity<List<HistoryEntity>> listHttpResultEntity) {
                if (!listHttpResultEntity.isNull()) {
                    getView().showHistory(listHttpResultEntity.getData().get(0));
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

    public void queryIdiom(String name) {
        subscribe(mApiStore.queryIdiom(name), new ResponseListener<HttpResultEntity<IdiomEntity>>() {
            @Override
            public void onSuccess(HttpResultEntity<IdiomEntity> idiomEntityHttpResultEntity) {
                if(!idiomEntityHttpResultEntity.isNull()) {
                    getView().showIdiom(idiomEntityHttpResultEntity.getData());
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

    public void queryDictionary(String name) {
        subscribe(mApiStore.queryDictionary(name), new ResponseListener<HttpResultEntity<DictionaryEntity>>() {
            @Override
            public void onSuccess(HttpResultEntity<DictionaryEntity> idiomEntityHttpResultEntity) {
                if(!idiomEntityHttpResultEntity.isNull()) {
                    getView().showDictionary(idiomEntityHttpResultEntity.getData());
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

    public void horoscope(String date, String hour) {
        subscribe(mApiStore.horoscope(date, hour), new ResponseListener<HttpResultEntity<HoroscopeEntity>>() {
            @Override
            public void onSuccess(HttpResultEntity<HoroscopeEntity> horoscopeEntityHttpResultEntity) {
                if(!horoscopeEntityHttpResultEntity.isNull()) {
                    getView().showHoroscope(horoscopeEntityHttpResultEntity.getData());
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
