package com.dms.base.baseapplication.ui.activity;


import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.entity.DictionaryEntity;
import com.dms.base.baseapplication.entity.HistoryEntity;
import com.dms.base.baseapplication.entity.HoroscopeEntity;
import com.dms.base.baseapplication.entity.IdiomEntity;
import com.dms.base.baseapplication.mvp.presenter.MobPresenter;
import com.dms.base.baseapplication.mvp.view.MobView;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseUIActivity<MobPresenter> implements MobView {

    @BindView(R.id.input_et)
    EditText inputEt;
    @BindView(R.id.result_tv)
    TextView resultTv;

    private String mInput;


    @Override
    public MobPresenter createPresenter() {
        return new MobPresenter();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mTitleBar.setTitle("测试");
    }

    @OnClick(R.id.get_data_btn)
    public void onViewClicked() {
        mInput = inputEt.getText().toString();
        if(!TextUtils.isEmpty(mInput)) {
            mPresenter.queryIdiom(mInput);
        }
    }

    @Override
    protected void onErrorAndEmptyAction() {
        super.onErrorAndEmptyAction();
        mPresenter.queryIdiom(mInput);
    }

    @Override
    public void showHistory(HistoryEntity historyEntity) {

    }

    @Override
    public void showDictionary(DictionaryEntity dictionaryEntity) {

    }

    @Override
    public void showIdiom(IdiomEntity idiomEntity) {
        resultTv.setText(idiomEntity.toString());
    }

    @Override
    public void showHoroscope(HoroscopeEntity horoscopeEntity) {

    }
}

