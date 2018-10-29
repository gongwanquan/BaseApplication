package com.dms.base.baseapplication.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.dms.base.baseapplication.model.DictionaryEntity;
import com.dms.base.baseapplication.model.HistoryEntity;
import com.dms.base.baseapplication.model.HoroscopeEntity;
import com.dms.base.baseapplication.model.IdiomEntity;
import com.dms.base.baseapplication.model.PerpetualCalendar;
import com.dms.base.baseapplication.mvp.presenter.MobPresenter;
import com.dms.base.baseapplication.mvp.view.MobView;
import com.dms.base.baseapplication.R;
import com.dms.base.baseproject.ui.fragment.BaseUiFragment;

import java.util.List;

import butterknife.BindView;

public class DictionaryFragment extends BaseUiFragment<MobPresenter> implements MobView {

    @BindView(R.id.dictionary_tv)
    TextView dictionaryTv;

    private String mKeyWords;

    public static DictionaryFragment createInstance(String keywords) {
        DictionaryFragment fragment = new DictionaryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("extra_keywords", keywords);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dictionary;
    }


    @Override
    public MobPresenter createPresenter() {
        return new MobPresenter();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle bundle = getArguments();
        if (null != bundle) {
            mKeyWords = bundle.getString("extra_keywords");
            mPresenter.queryDictionary(mKeyWords);
        }

    }

    @Override
    protected void onErrorAndEmptyAction() {
        super.onErrorAndEmptyAction();
        mPresenter.queryDictionary(mKeyWords);
    }

    @Override
    public void showHistory(List<HistoryEntity> historyEntity) {

    }

    @Override
    public void showDictionary(DictionaryEntity dictionaryEntity) {
        dictionaryTv.setText(dictionaryEntity.toString());
    }

    @Override
    public void showIdiom(IdiomEntity idiomEntity) {

    }

    @Override
    public void showHoroscope(HoroscopeEntity horoscopeEntity) {

    }

    @Override
    public void showPerpetual(PerpetualCalendar perpetualCalendar) {

    }
}
