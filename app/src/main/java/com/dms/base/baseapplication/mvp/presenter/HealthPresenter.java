package com.dms.base.baseapplication.mvp.presenter;

import com.dms.base.baseapplication.model.BaseResponse;
import com.dms.base.baseapplication.model.HealthEntity;
import com.dms.base.baseapplication.model.PageEntity;
import com.dms.base.baseapplication.mvp.view.HealthView;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;

public class HealthPresenter extends BasePresenter<HealthView> {
    private int mPage = 0;

    private final int SIZE = 10;


    public void searchHealth(final boolean isRefresh, String name) {
        if(isRefresh) {
            mPage = 0;
        }

        subscribe(ApiManager.getHealthService().healthSearch(mPage + 1, SIZE, name), new ResponseListener<BaseResponse<PageEntity<HealthEntity>>>() {
            @Override
            public void onSuccess(BaseResponse<PageEntity<HealthEntity>> pageEntityBaseResponse) {
                mPage++;
                getView().showData(isRefresh, pageEntityBaseResponse.getData());
            }

            @Override
            public boolean handleError(NetError netError) {
                getView().loadDataFail(isRefresh);
                return false;
            }
        });
    }
}
