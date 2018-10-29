package com.dms.base.baseapplication.net;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;

import okhttp3.logging.HttpLoggingInterceptor;

public class MyLogger implements HttpLoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        if (isJsonStr(message)) {
            LogUtils.json(message);
        } else {
            LogUtils.d(message);
        }
    }

    private boolean isJsonStr(String str) {
        boolean result = false;

        if (!TextUtils.isEmpty(str)) {
            str = str.trim();
            if (str.startsWith("{") && str.endsWith("}")) {
                result = true;
            } else if (str.startsWith("[") && str.endsWith("]")) {
                result = true;
            }
        }

        return result;
    }
}
