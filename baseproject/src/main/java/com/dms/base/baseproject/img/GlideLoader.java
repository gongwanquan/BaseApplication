package com.dms.base.baseproject.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;


public class GlideLoader implements ILoader {

    @Override
    public void displayImg(Context context, Object sourceObj, ImageView targetView, LoaderOptions options) {
        getRequestBuilder(context, sourceObj, options).into(targetView);
    }

    @Override
    public void loadWithCallback(Context context, Object sourceObj, final LoadCallback loadCallback, LoaderOptions options) {
        getRequestBuilder(context, sourceObj, options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        loadCallback.onLoadReady(resource);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        loadCallback.onLoadFailed();
                    }
                });
    }

    @Override
    public void clearMemoryCache(Context context) {
        GlideApp.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        GlideApp.get(context).clearDiskCache();
    }

    @Override
    public void resume(Context context) {
        GlideApp.with(context).resumeRequests();
    }

    @Override
    public void pause(Context context) {
        GlideApp.with(context).pauseRequests();
    }


    private RequestBuilder getRequestBuilder(Context context, Object sourceObj, LoaderOptions options) {
        RequestBuilder requestBuilder = GlideApp.with(context).load(sourceObj);
        if (null == options) {
            return requestBuilder;
        }

        return wrapRequestBuilder(requestBuilder, options);
    }

    private RequestBuilder wrapRequestBuilder(RequestBuilder requestBuilder, LoaderOptions options) {
        RequestOptions requestOptions = new com.bumptech.glide.request.RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .priority(Priority.HIGH);

        if (options.getLoadingResId() != -1) {
            requestOptions.placeholder(options.getLoadingResId());
        }

        if (options.getLoadErrorResId() != -1) {
            requestOptions.error(options.getLoadErrorResId());
        }

        if (null != options.getImgReSize()) {
            requestOptions.override(options.getImgReSize().getReWidth(), options.getImgReSize().getReHeight());
        }

        if (options.isCircle()) {
            requestOptions.transform(new CircleCrop());
        } else if (options.getRadius() > 0) {
            requestOptions.transform(new RoundedCorners(SizeUtils.dp2px(options.getRadius())));
        }

        if (options.isCrossFade()) {
            requestBuilder.transition(DrawableTransitionOptions.withCrossFade());

        }

        if(options.isSkipCache()) {
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        }

        if (null != options.getAnimator()) {
            requestBuilder.transition(GenericTransitionOptions.with(options.getAnimator()));
        }

        return requestBuilder.apply(requestOptions);
    }

}
