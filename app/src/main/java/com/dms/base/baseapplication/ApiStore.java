package com.dms.base.baseapplication;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 龚万全 on 2016/9/27.
 * Description:
 */

public interface ApiStore {

    @GET("appstore/history/query")
    Observable<HttpResultEntity<List<HistoryEntity>>> getHistory(@Query("day") String day);



}
