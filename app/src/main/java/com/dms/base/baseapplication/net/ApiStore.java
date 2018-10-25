package com.dms.base.baseapplication.net;

import com.dms.base.baseapplication.model.DictionaryEntity;
import com.dms.base.baseapplication.model.HistoryEntity;
import com.dms.base.baseapplication.model.HoroscopeEntity;
import com.dms.base.baseapplication.model.BaseResponse;
import com.dms.base.baseapplication.model.IdiomEntity;
import com.dms.base.baseapplication.model.PerpetualCalendar;

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
    Observable<BaseResponse<List<HistoryEntity>>> getHistory(@Query("day") String day);

    @GET("appstore/idiom/query")
    Observable<BaseResponse<IdiomEntity>> queryIdiom(@Query("name") String name);

    @GET("appstore/dictionary/query")
    Observable<BaseResponse<DictionaryEntity>> queryDictionary(@Query("name") String name);

    @GET("appstore/horoscope/day")
    Observable<BaseResponse<HoroscopeEntity>> horoscope(@Query("date")String date, @Query("hour") String hour);

    @GET("appstore/calendar/day")
    Observable<BaseResponse<PerpetualCalendar>> day(@Query("date") String date);



}
