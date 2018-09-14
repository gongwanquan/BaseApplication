package com.dms.base.baseapplication;

import android.content.Context;
import android.widget.ImageView;

import com.dms.base.baseproject.img.LoaderFactory;
import com.dms.base.baseproject.img.LoaderOptions;

public class ImgLoaderManager {
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        LoaderOptions.Builder builder = new LoaderOptions.Builder();
        LoaderOptions options = builder
                .setRadius(5)
                .setCrossFade(true)
                .build();
        LoaderFactory.getLoader().displayImg(context, url, imageView, options);
    }
}
