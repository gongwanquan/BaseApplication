package com.dms.base.baseproject.img;

import android.graphics.drawable.Drawable;

/**
 * Created by wanglei on 2016/12/21.
 */

public abstract class LoadCallback {
    void onLoadFailed() {}

    public abstract void onLoadReady(Drawable drawable);
}
