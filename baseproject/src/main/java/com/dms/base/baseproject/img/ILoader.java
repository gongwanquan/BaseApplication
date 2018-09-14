package com.dms.base.baseproject.img;

import android.content.Context;
import android.widget.ImageView;


public interface ILoader {


    void displayImg(Context context, Object sourceObj, ImageView targetView,  LoaderOptions options);

    void loadWithCallback(Context context, Object sourceObj, LoadCallback loadCallback, LoaderOptions options);
}
