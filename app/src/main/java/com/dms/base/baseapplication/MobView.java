package com.dms.base.baseapplication;

import com.dms.base.baseproject.mvp.IView;

public interface MobView extends IView<MobPresenter> {

    void showHistory(HistoryEntity historyEntity);

    void showDictionary(DictionaryEntity dictionaryEntity);

    void showIdiom(IdiomEntity idiomEntity);

    void showHoroscope(HoroscopeEntity horoscopeEntity);
}
