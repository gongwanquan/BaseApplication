package com.dms.base.baseproject.img;

public class LoaderFactory {

    private static class InstanceHolder {
        public static ILoader mILoader = new GlideLoader();
    }

    public static ILoader getLoader() {
        return InstanceHolder.mILoader;
    }


}
