package com.dms.base.baseproject.mvp.provider;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.dms.base.baseproject.mvp.presenter.IPresenter;
import com.dms.base.baseproject.ui.activity.BaseActivity;
import com.dms.base.baseproject.ui.fragment.BaseFragment;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class PresenterProvider {

    @Nullable
    public static <P extends IPresenter> P createPresenter(@NonNull BaseActivity baseActivity) {
        return createPresenter(baseActivity.getClass());
    }

    @Nullable
    public static <P extends IPresenter> P createPresenter(@NonNull BaseFragment baseFragment) {
        return createPresenter(baseFragment.getClass());
    }

    @Nullable
    private static <P extends IPresenter> P createPresenter(@NonNull Class<?> c) {
        Type genericSuperclass = c.getGenericSuperclass();

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
