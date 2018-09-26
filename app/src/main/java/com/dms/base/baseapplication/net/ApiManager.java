package com.dms.base.baseapplication.net;

import com.dms.base.baseproject.net.ApiClient;

public class ApiManager {
    public static ApiStore mApiStore;

    public static ApiStore getApiStore() {
        if(null == mApiStore) {
            mApiStore = ApiClient.getInstance().getRetrofit("http://apicloud.mob.com/", null)
                    .create(ApiStore.class);
        }
        return mApiStore;
    }
}
