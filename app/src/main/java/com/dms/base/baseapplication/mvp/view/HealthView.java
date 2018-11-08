package com.dms.base.baseapplication.mvp.view;

import com.dms.base.baseapplication.model.HealthEntity;
import com.dms.base.baseapplication.model.PageEntity;
import com.dms.base.baseproject.mvp.view.IView;

public interface HealthView extends IView {

    void showData(boolean isRefresh, PageEntity<HealthEntity> healthEntityPageEntity);

    void loadDataFail(boolean isRefresh);
}
