package com.dms.base.baseapplication;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

public class DictionaryFragment extends BaseUiFragment<MobPresenter> implements MobView {

    @BindView(R.id.dictionary_tv)
    TextView dictionaryTv;

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
    protected void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if(null != bundle) {
            String keywords = bundle.getString("extra_keywords");
            mPresenter.queryDictionary(keywords);
        }
    }

    @Override
    public void showHistory(HistoryEntity historyEntity) {

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
}
