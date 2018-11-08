package com.dms.base.baseapplication.net.api;

import android.net.Uri;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FileService {

    @GET
    Observable<ResponseBody> downloadFile(@Url Uri uri);
}
