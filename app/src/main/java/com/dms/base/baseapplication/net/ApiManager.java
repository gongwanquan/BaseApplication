package com.dms.base.baseapplication.net;

import com.dms.base.baseapplication.net.api.ApiStore;
import com.dms.base.baseapplication.net.api.FileService;
import com.dms.base.baseapplication.net.api.HealthService;
import com.dms.base.baseapplication.net.api.UserService;
import com.dms.base.baseproject.net.ApiClient;

public class ApiManager {
    private static ApiStore mApiStore;

    private static UserService mUserService;

    private static FileService mFileService;

    private static HealthService mHealthService;

    public static ApiStore getApiStore() {
        if (null == mApiStore) {
            mApiStore = ApiClient.getInstance()
                    .getRetrofit()
                    .create(ApiStore.class);
        }
        return mApiStore;
    }

    public static UserService getUserService() {
        if (null == mUserService) {
            mUserService = ApiClient.getInstance()
                    .getRetrofit()
                    .create(UserService.class);
        }

        return mUserService;
    }

    public static FileService getFileService() {
        if(null == mFileService) {
            mFileService = ApiClient.getInstance()
                    .getRetrofit()
                    .create(FileService.class);
        }

        return mFileService;
    }

    public static HealthService getHealthService() {
        if(null == mHealthService) {
            mHealthService = ApiClient.getInstance()
                    .getRetrofit()
                    .create(HealthService.class);
        }

        return mHealthService;
    }
}
