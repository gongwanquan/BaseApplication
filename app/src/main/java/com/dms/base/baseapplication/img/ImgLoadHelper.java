package com.dms.base.baseapplication.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.dms.base.baseapplication.R;
import com.dms.base.baseproject.img.LoadCallback;
import com.dms.base.baseproject.img.LoaderFactory;
import com.dms.base.baseproject.img.LoaderOptions;

public class ImgLoadHelper {

    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        LoaderOptions.Builder builder = new LoaderOptions.Builder();
        LoaderOptions options = builder
                .setRadius(5)
                .setCrossFade(true)
                .build();
        LoaderFactory.getLoader().displayImg(context, url, imageView, options);
    }

    public static void loadHeadLogo(Context context, String url, final ImageView imageView) {
        LoaderOptions.Builder builder = new LoaderOptions.Builder();
        LoaderOptions options = builder
                .setCircle(true)
                .setLoadErrorResId(R.drawable.ic_launcher_background)
                .setLoadErrorResId(R.drawable.ic_launcher_background)
                .setCrossFade(true)
                .build();
        LoaderFactory.getLoader().loadWithCallback(context, url, new LoadCallback() {
            @Override
            public void onLoadReady(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        }, options);
    }
}
