package com.dms.base.baseproject.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;


public class GlideLoader implements ILoader {

    @Override
    public void displayImg(Context context, Object sourceObj, ImageView targetView, LoaderOptions options) {
        getRequestBuilder(context, sourceObj, options).into(targetView);
    }

    @Override
    public void loadWithCallback(Context context, Object sourceObj, final LoadCallback loadCallback, LoaderOptions options) {
        getRequestBuilder(context, sourceObj, options).into(new Target<Drawable>() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                loadCallback.onLoadFailed();
            }

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                loadCallback.onLoadReady(resource);
            }


            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void getSize(@NonNull SizeReadyCallback cb) {

            }

            @Override
            public void removeCallback(@NonNull SizeReadyCallback cb) {

            }

            @Override
            public void setRequest(@Nullable Request request) {

            }

            @Nullable
            @Override
            public Request getRequest() {
                return null;
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {

            }

            @Override
            public void onDestroy() {

            }
        });
    }


    private RequestBuilder getRequestBuilder(Context context, Object sourceObj, LoaderOptions options) {
        RequestBuilder requestBuilder = Glide.with(context).load(sourceObj);
        if (null == options) {
            return requestBuilder;
        }

        return wrapRequestBuilder(requestBuilder, options);
    }

    private RequestBuilder wrapRequestBuilder(RequestBuilder requestBuilder, LoaderOptions options) {
        com.bumptech.glide.request.RequestOptions requestOptions = new com.bumptech.glide.request.RequestOptions()
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

        requestOptions.skipMemoryCache(options.isSkipMemoryCache());

        if (null != options.getAnimator()) {
            requestBuilder.transition(GenericTransitionOptions.with(options.getAnimator()));
        }

        return requestBuilder.apply(requestOptions);
    }

}
