package com.dms.base.baseapplication.mvp.presenter;

import com.dms.base.baseapplication.model.DictionaryEntity;
import com.dms.base.baseapplication.model.HistoryEntity;
import com.dms.base.baseapplication.model.HoroscopeEntity;
import com.dms.base.baseapplication.model.BaseResponse;
import com.dms.base.baseapplication.model.IdiomEntity;
import com.dms.base.baseapplication.mvp.view.MobView;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseapplication.net.ApiStore;
import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;
import java.util.List;

public class MobPresenter extends BasePresenter<MobView> {
    private ApiStore mApiStore;

    public MobPresenter() {
        mApiStore = ApiManager.getApiStore();
    }

    public void queryHistory(String date) {

        subscribe(mApiStore.getHistory(date), new ResponseListener<BaseResponse<List<HistoryEntity>>>() {
            @Override
            public void onSuccess(BaseResponse<List<HistoryEntity>> listBaseResponse) {
                getView().showHistory(listBaseResponse.getData());
            }

            @Override
            public boolean handleError(NetError netError) {
                if(netError.isNoDataError()) {
                    getView().showMessage("查询不到数据");
                    return true;
                }
                return false;
            }

        });
    }

    public void queryIdiom(String name) {
        subscribe(mApiStore.queryIdiom(name), new ResponseListener<BaseResponse<IdiomEntity>>() {
            @Override
            public void onSuccess(BaseResponse<IdiomEntity> idiomEntityBaseResponse) {
                getView().showIdiom(idiomEntityBaseResponse.getData());
            }

            @Override
            public boolean handleError(NetError netError) {
                if(netError.isNoDataError()) {
                    getView().showMessage("查询不到数据");
                    return true;
                }
                return false;
            }

        });
    }

    public void queryDictionary(String name) {
        subscribe(mApiStore.queryDictionary(name), new ResponseListener<BaseResponse<DictionaryEntity>>() {
            @Override
            public void onSuccess(BaseResponse<DictionaryEntity> idiomEntityBaseResponse) {
                getView().showDictionary(idiomEntityBaseResponse.getData());
            }

            @Override
            public boolean handleError(NetError netError) {
                if(netError.isNoDataError()) {
                    getView().showMessage("查询不到数据");
                    return true;
                }
                return false;
            }


        });
    }

    public void queryHoroscope(String date, String hour) {
        subscribe(mApiStore.horoscope(date, hour), new ResponseListener<BaseResponse<HoroscopeEntity>>() {
            @Override
            public void onSuccess(BaseResponse<HoroscopeEntity> horoscopeEntityBaseResponse) {
                getView().showHoroscope(horoscopeEntityBaseResponse.getData());
            }

            @Override
            public boolean handleError(NetError netError) {
                if(netError.isNoDataError()) {
                    getView().showMessage("查询不到数据");
                    return true;
                }
                return false;
            }

        });
    }


}
