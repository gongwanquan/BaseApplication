package com.dms.base.baseapplication;

import com.dms.base.baseproject.mvp.IView;

public interface MobView extends IView {

    void showIdiom(IdiomEntity idiomEntity);

    void showDictionary(DictionaryEntity dictionaryEntity);

    void showHoroscope(HoroscopeEntity horoscopeEntity);
}
