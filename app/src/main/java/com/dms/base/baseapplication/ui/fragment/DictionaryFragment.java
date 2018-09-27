package com.dms.base.baseapplication.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.dms.base.baseapplication.entity.DictionaryEntity;
import com.dms.base.baseapplication.entity.HistoryEntity;
import com.dms.base.baseapplication.entity.HoroscopeEntity;
import com.dms.base.baseapplication.entity.IdiomEntity;
import com.dms.base.baseapplication.mvp.presenter.MobPresenter;
import com.dms.base.baseapplication.mvp.view.MobView;
import com.dms.base.baseapplication.R;
import com.dms.base.baseproject.ui.fragment.BaseUiFragment;

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
    protected void initData() {
        super.initData();
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
