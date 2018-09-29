package com.dms.base.baseapplication.mvp.presenter;

import android.text.TextUtils;
import android.util.Base64;

import com.blankj.utilcode.util.CacheDoubleUtils;
import com.dms.base.baseapplication.entity.BaseResponse;
import com.dms.base.baseapplication.entity.UserEntity;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseapplication.ui.fragment.EditUserInfoFragment;
import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;
import com.google.gson.Gson;

public class EditUserInfoPresenter extends BasePresenter<EditUserInfoFragment> {

    public void editUserInfo(String name, String sex, String age) {

        UserEntity userEntity = CacheDoubleUtils.getInstance().getParcelable("login_user", UserEntity.CREATOR);
        if (null == userEntity) {
            return;
        }

        UserEntity.UserInfo userInfo = new UserEntity.UserInfo();
        userInfo.setName(name);

        if (TextUtils.equals("男", sex)) {
            userInfo.setSex(0);
        } else if (TextUtils.equals("男", sex)) {
            userInfo.setSex(1);
        }

        userInfo.setAge(Integer.valueOf(age));

        String value = new Gson().toJson(userInfo);
        value = Base64.encodeToString(value.getBytes(), Base64.DEFAULT);

        subscribe(ApiManager.getUserService().put(userEntity.getToken(), userEntity.getUid(), "user_info", value), new ResponseListener<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                getView().onEditUserInfoSuccess();
            }

            @Override
            public boolean handleError(NetError netError) {
                return false;
            }
        });
    }
}
