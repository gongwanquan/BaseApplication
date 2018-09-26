package com.dms.base.baseproject.img;

import android.content.Context;
import android.widget.ImageView;


public interface ILoader {


    void displayImg(Context context, Object sourceObj, ImageView targetView,  LoaderOptions options);

    void loadWithCallback(Context context, Object sourceObj, LoadCallback loadCallback, LoaderOptions options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    void resume(Context context);

    void pause(Context context);
}
