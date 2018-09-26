package com.dms.base.baseapplication.mvp.view;

import com.dms.base.baseapplication.entity.DictionaryEntity;
import com.dms.base.baseapplication.entity.HistoryEntity;
import com.dms.base.baseapplication.entity.HoroscopeEntity;
import com.dms.base.baseapplication.entity.IdiomEntity;
import com.dms.base.baseapplication.mvp.presenter.MobPresenter;
import com.dms.base.baseproject.mvp.IView;

public interface MobView extends IView<MobPresenter> {

    void showHistory(HistoryEntity historyEntity);

    void showDictionary(DictionaryEntity dictionaryEntity);

    void showIdiom(IdiomEntity idiomEntity);

    void showHoroscope(HoroscopeEntity horoscopeEntity);
}
