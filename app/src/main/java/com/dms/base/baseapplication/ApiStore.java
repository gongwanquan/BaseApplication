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

    @GET("appstore/idiom/query")
    Observable<HttpResultEntity<IdiomEntity>> queryIdiom(@Query("name") String name);

    @GET("appstore/dictionary/query")
    Observable<HttpResultEntity<DictionaryEntity>> queryDictionary(@Query("name") String name);

    @GET("appstore/horoscope/day")
    Observable<HttpResultEntity<HoroscopeEntity>> horoscope(@Query("date")String date, @Query("hour") String hour);


}
