package com.dms.base.baseapplication.net;


import com.dms.base.baseapplication.entity.BaseResponse;
import com.dms.base.baseapplication.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("user/rigister")
    Observable<BaseResponse<String>> register(@Query("username") String username, @Query("password") String password);

    @GET("user/login")
    Observable<BaseResponse<UserEntity>> login(@Query("username") String username, @Query("password") String password);

    @GET("user/profile/put")
    Observable<BaseResponse> put(@Query("token") String token, @Query("uid") String uid, @Query("item") String item, @Query("value") String value);

    @GET("user/profile/query")
    Observable<BaseResponse<String>> query(@Query("uid") String uid, @Query("item") String item);

    @GET("/user/password/change")
    Observable<BaseResponse> changePassword(@Query("username") String username, @Query("oldPassword") String oldPassword, @Query("newPassword") String newPassword);
}
