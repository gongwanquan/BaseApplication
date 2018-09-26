package com.dms.base.baseapplication.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.dms.base.baseapplication.entity.DictionaryEntity;
import com.dms.base.baseapplication.entity.HistoryEntity;
import com.dms.base.baseapplication.entity.HoroscopeEntity;
import com.dms.base.baseapplication.entity.HttpResultEntity;
import com.dms.base.baseapplication.entity.IdiomEntity;
import com.dms.base.baseapplication.mvp.view.MobView;
import com.dms.base.baseapplication.net.ApiStore;
import com.dms.base.baseproject.mvp.BasePresenter;
import com.dms.base.baseproject.net.ApiClient;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;

import java.util.List;

public class MobPresenter extends BasePresenter<MobView> {
    private ApiStore mApiStore;

    public MobPresenter() {
        mApiStore = ApiClient.getInstance()
                .getRetrofit("http://apicloud.mob.com/", null)
                .create(ApiStore.class);
    }

    public void queryHistory(String date) {

        subscribe(mApiStore.getHistory(date), new ResponseListener<HttpResultEntity<List<HistoryEntity>>>() {
            @Override
            public void onSuccess(HttpResultEntity<List<HistoryEntity>> listHttpResultEntity) {
                if (!listHttpResultEntity.isNull()) {
                    getView().showHistory(listHttpResultEntity.getData().get(0));
                }
            }

            @Override
            public boolean handleError(NetError netError) {
                return false;
            }

        });
    }

    public void queryIdiom(String name) {
        subscribe(mApiStore.queryIdiom(name), new ResponseListener<HttpResultEntity<IdiomEntity>>() {
            @Override
            public void onSuccess(HttpResultEntity<IdiomEntity> idiomEntityHttpResultEntity) {
                if (!idiomEntityHttpResultEntity.isNull()) {
                    getView().showIdiom(idiomEntityHttpResultEntity.getData());
                }
            }

            @Override
            public boolean handleError(NetError netError) {
                return false;
            }

        });
    }

    public void queryDictionary(String name) {
        subscribe(mApiStore.queryDictionary(name), new ResponseListener<HttpResultEntity<DictionaryEntity>>() {
            @Override
            public void onSuccess(HttpResultEntity<DictionaryEntity> idiomEntityHttpResultEntity) {
                getView().showDictionary(idiomEntityHttpResultEntity.getData());
            }

            @Override
            public boolean handleError(NetError netError) {
                if(netError.isAuthError()) {
                    ToastUtils.showShort(netError.getMessage());
                    return true;
                }
                return false;
            }


        });
    }

    public void horoscope(String date, String hour) {
        subscribe(mApiStore.horoscope(date, hour), new ResponseListener<HttpResultEntity<HoroscopeEntity>>() {
            @Override
            public void onSuccess(HttpResultEntity<HoroscopeEntity> horoscopeEntityHttpResultEntity) {
                if (!horoscopeEntityHttpResultEntity.isNull()) {
                    getView().showHoroscope(horoscopeEntityHttpResultEntity.getData());
                }
            }

            @Override
            public boolean handleError(NetError netError) {
                return false;
            }

        });
    }


}
