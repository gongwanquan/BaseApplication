package com.dms.base.baseproject.mvp.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dms.base.baseproject.mvp.presenter.IPresenter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class PresenterFactory {

    @Nullable
    public static <P extends IPresenter> P createPresenter(@NonNull Object o) {
        Type genericSuperclass = o.getClass().getGenericSuperclass();

        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                try {
                    Class<P> entityClass = (Class<P>) actualTypeArguments[0];
                    return entityClass.newInstance();
                } catch (ClassCastException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
