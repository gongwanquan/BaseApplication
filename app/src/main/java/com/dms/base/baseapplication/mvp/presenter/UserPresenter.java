package com.dms.base.baseapplication.mvp.presenter;

import android.text.TextUtils;

import com.blankj.utilcode.util.CacheDoubleUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.dms.base.baseapplication.entity.BaseResponse;
import com.dms.base.baseapplication.entity.UserEntity;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseapplication.ui.activity.UserActivity;
import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;


public class UserPresenter extends BasePresenter<UserActivity> {

    public void register(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            getView().showMessage("用户名或密码不能为空");
            return;
        }

        password = EncryptUtils.encryptMD5ToString(password);

        subscribe(ApiManager.getUserService().register(username, password), new ResponseListener<BaseResponse<String>>() {
            @Override
            public void onSuccess(BaseResponse<String> stringBaseResponse) {
                getView().onRegisterSuccess(stringBaseResponse.getData());
            }

            @Override
            public boolean handleError(NetError netError) {
                if (netError.isBusinessError()) {
                    getView().showMessage(netError.getMessage());
                    return true;
                }
                return false;
            }
        });
    }

    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            getView().showMessage("用户名或密码不能为空");
            return;
        }

        password = EncryptUtils.encryptMD5ToString(password);

        subscribe(ApiManager.getUserService().login(username, password), new ResponseListener<BaseResponse<UserEntity>>() {


            @Override
            public void onSuccess(BaseResponse<UserEntity> userEntityBaseResponse) {
                getView().onLoginSuccess(userEntityBaseResponse.getData());
            }

            @Override
            public boolean handleError(NetError netError) {
                if (netError.isNoDataError() || netError.isBusinessError()) {
                    getView().showMessage(netError.getMessage());
                    return true;
                }
                return false;
            }
        });
    }

}
