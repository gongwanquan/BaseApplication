package com.dms.base.baseapplication.mvp.presenter;

import com.blankj.utilcode.util.CacheDoubleUtils;
import com.dms.base.baseapplication.entity.BaseResponse;
import com.dms.base.baseapplication.entity.UserEntity;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseapplication.ui.fragment.EditUserInfoFragment;
import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;

import io.reactivex.Observable;
import io.reactivex.functions.Function3;

public class EditUserInfoPresenter extends BasePresenter<EditUserInfoFragment> {


    public void editUserInfo(String name, String sex, String age) {

        UserEntity userEntity = CacheDoubleUtils.getInstance().getParcelable("login_user", UserEntity.CREATOR);
        if (null == userEntity) {
            return;
        }

        Observable<BaseResponse> nameObservable = getObservable(userEntity, "name", name);
        Observable<BaseResponse> sexObservable = getObservable(userEntity, "sex", sex);
        Observable<BaseResponse> ageObservable = getObservable(userEntity, "age", age);


        subscribe(Observable.zip(nameObservable, sexObservable, ageObservable, new Function3<BaseResponse, BaseResponse, BaseResponse, BaseResponse>() {
            @Override
            public BaseResponse apply(BaseResponse baseResponse1, BaseResponse baseResponse2, BaseResponse baseResponse3) throws Exception {
                if(!baseResponse1.isSuccess()) {
                    return baseResponse1;
                }
                if(!baseResponse2.isSuccess()) {
                    return baseResponse2;
                }
                if(!baseResponse3.isSuccess()) {
                    return baseResponse3;
                }

                return baseResponse3;
            }
        }), new ResponseListener<BaseResponse>() {
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


    public Observable<BaseResponse> getObservable(UserEntity userEntity, String item, String value) {
        return ApiManager.getUserService().put(userEntity.getToken(), userEntity.getUid(), item, value);
    }
}
