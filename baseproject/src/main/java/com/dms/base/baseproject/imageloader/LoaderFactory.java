package com.dms.base.baseproject.imageloader;

/**
 * Created by wanglei on 2016/11/28.
 */
public class LoaderFactory {

    private static ILoader loader;


    public static ILoader getLoader() {
        if (loader == null) {
            synchronized (LoaderFactory.class) {
                if (loader == null) {
                    loader = new GlideLoader();
                }
            }
        }
        return loader;
    }


}
