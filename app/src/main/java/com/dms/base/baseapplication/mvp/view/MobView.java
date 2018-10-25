package com.dms.base.baseapplication.mvp.view;

import com.dms.base.baseapplication.model.DictionaryEntity;
import com.dms.base.baseapplication.model.HistoryEntity;
import com.dms.base.baseapplication.model.HoroscopeEntity;
import com.dms.base.baseapplication.model.IdiomEntity;
import com.dms.base.baseapplication.model.PerpetualCalendar;
import com.dms.base.baseproject.mvp.view.IView;

import java.util.List;

public interface MobView extends IView {

    void showHistory(List<HistoryEntity> historyEntity);

    void showDictionary(DictionaryEntity dictionaryEntity);

    void showIdiom(IdiomEntity idiomEntity);

    void showHoroscope(HoroscopeEntity horoscopeEntity);

    void showPerpetual(PerpetualCalendar perpetualCalendar);
}
