package com.dms.base.baseproject.img;


import android.app.TimePickerDialog;

import com.bumptech.glide.request.transition.ViewPropertyTransition;

public class LoaderOptions {
    //加载过程中显示的图片ID
    private int mLoadingResId = -1;
    //加载失败时显示的图片ID
    private int mLoadErrorResId = -1;
    //重新设置的容器宽高
    private ImgReSize mImgReSize;
    //是否显示圆形图片
    private boolean mIsCircle = false;
    //圆角矩形图片角度(单位为dp)
    private float mRadius = 0f;
    //是否平滑加载图片
    private boolean mIsCrossFade = false;
    //是否跳过缓存
    private boolean mIsSkipMemoryCache = false;
    //图片加载动画
    private ViewPropertyTransition.Animator mAnimator;

    private LoaderOptions(Builder builder) {
        mLoadingResId = builder.mLoadingResId;
        mLoadErrorResId = builder.mLoadErrorResId;
        mImgReSize = builder.mImgReSize;
        mIsCircle = builder.mIsCircle;
        mRadius = builder.mRadius;
        mIsCrossFade = builder.mIsCrossFade;
        mIsSkipMemoryCache = builder.mIsSkipMemoryCache;
        mAnimator = builder.mAnimator;
    }

    public int getLoadingResId() {
        return mLoadingResId;
    }

    public int getLoadErrorResId() {
        return mLoadErrorResId;
    }

    public ImgReSize getImgReSize() {
        return mImgReSize;
    }

    public boolean isCircle() {
        return mIsCircle;
    }

    public float getRadius() {
        return mRadius;
    }

    public boolean isCrossFade() {
        return mIsCrossFade;
    }

    public boolean isSkipMemoryCache() {
        return mIsSkipMemoryCache;
    }

    public ViewPropertyTransition.Animator getAnimator() {
        return mAnimator;
    }

    public class ImgReSize {
        private int mReWidth = 0;
        private int mReHeight = 0;

        public ImgReSize(int reWidth, int reHeight) {
            this.mReWidth = reWidth;
            this.mReHeight = reHeight;
        }

        public int getReWidth() {
            return mReWidth;
        }

        public void setReWidth(int reWidth) {
            mReWidth = reWidth;
        }

        public int getReHeight() {
            return mReHeight;
        }

        public void setReHeight(int reHeight) {
            mReHeight = reHeight;
        }
    }

    public final static class Builder {
        //加载过程中显示的图片ID
        private int mLoadingResId = -1;
        //加载失败时显示的图片ID
        private int mLoadErrorResId = -1;
        //重新设置的容器宽高
        private ImgReSize mImgReSize;
        //是否显示圆形图片
        private boolean mIsCircle = false;
        //圆角矩形图片角度(单位为dp)
        private float mRadius = 0f;
        //是否平滑加载图片
        private boolean mIsCrossFade = false;
        //是否跳过缓存
        private boolean mIsSkipMemoryCache = false;
        //图片加载动画
        private ViewPropertyTransition.Animator mAnimator;

        public Builder setLoadingResId(int loadingResId) {
            mLoadingResId = loadingResId;
            return this;
        }

        public Builder setLoadErrorResId(int loadErrorResId) {
            mLoadErrorResId = loadErrorResId;
            return this;
        }

        public Builder setImgReSize(ImgReSize imgReSize) {
            mImgReSize = imgReSize;
            return this;
        }

        public Builder setCircle(boolean circle) {
            mIsCircle = circle;
            return this;
        }

        public Builder setRadius(float radius) {
            mRadius = radius;
            return this;
        }

        public Builder setCrossFade(boolean crossFade) {
            mIsCrossFade = crossFade;
            return this;
        }

        public Builder setSkipMemoryCache(boolean skipMemoryCache) {
            mIsSkipMemoryCache = skipMemoryCache;
            return this;
        }

        public Builder setAnimator(ViewPropertyTransition.Animator animator) {
            mAnimator = animator;
            return this;
        }

        public LoaderOptions build() {
            return new LoaderOptions(this);
        }
    }
}
