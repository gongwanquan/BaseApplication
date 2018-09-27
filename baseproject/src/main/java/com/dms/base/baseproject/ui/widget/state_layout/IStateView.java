package com.dms.base.baseproject.ui.widget.state_layout;

import android.view.View;

public interface IStateView {
    int LOADING = 0, EMPTY = 1, ERROR = 2, CONTENT = 3;


    void showLoading();

    void showEmpty();

    void showEmpty(int img, String title, String content);

    void showError();

    void showError(int img, String title, String content);

    void showContent();

    void setLoadingResource(int resource);

    void setErrorResource(int resource);

    void setEmptyResource(int resource);

    void setContentResource(int resource);

    void setEmptyAction(View.OnClickListener onClickListener);

    void setErrorAction(View.OnClickListener onClickListener);
}
