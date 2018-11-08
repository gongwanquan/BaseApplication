package com.dms.base.baseapplication.net.api;

import com.dms.base.baseapplication.model.BaseResponse;
import com.dms.base.baseapplication.model.HealthEntity;
import com.dms.base.baseapplication.model.PageEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HealthService {

    @GET("appstore/health/search")
    Observable<BaseResponse<PageEntity<HealthEntity>>> healthSearch(@Query("page") int page, @Query("size") int size, @Query("name") String name);
}
