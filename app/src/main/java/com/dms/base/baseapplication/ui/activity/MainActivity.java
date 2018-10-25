package com.dms.base.baseapplication.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.model.DictionaryEntity;
import com.dms.base.baseapplication.model.HistoryEntity;
import com.dms.base.baseapplication.model.HoroscopeEntity;
import com.dms.base.baseapplication.model.IdiomEntity;
import com.dms.base.baseapplication.model.PerpetualCalendar;
import com.dms.base.baseapplication.mvp.presenter.MobPresenter;
import com.dms.base.baseapplication.mvp.view.MobView;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseUIActivity<MobPresenter> implements MobView {

    @BindView(R.id.input_et)
    EditText inputEt;
    @BindView(R.id.date_et)
    EditText dateEt;
    @BindView(R.id.hour_et)
    EditText hourEt;
    @BindView(R.id.result_tv)
    TextView resultTv;
    @BindView(R.id.data_type_rg)
    RadioGroup dataTypeRg;

    private String mInput;

    private int mDataType = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setTitle("测试");

        dataTypeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.dictionary_rb) {
                    mDataType = 1;
                } else if (checkedId == R.id.idiom_rb) {
                    mDataType = 2;
                } else if (checkedId == R.id.history_rb) {
                    mDataType = 3;
                } else if (checkedId == R.id.horoscope_rb) {
                    mDataType = 4;
                } else if (checkedId == R.id.perpetual_rb) {
                    mDataType = 5;
                }

                if (mDataType == 4) {
                    inputEt.setVisibility(View.GONE);
                    dateEt.setVisibility(View.VISIBLE);
                    hourEt.setVisibility(View.VISIBLE);
                } else if (mDataType == 5) {
                    inputEt.setVisibility(View.GONE);
                    dateEt.setVisibility(View.VISIBLE);
                    hourEt.setVisibility(View.GONE);
                } else {
                    inputEt.setVisibility(View.VISIBLE);
                    dateEt.setVisibility(View.GONE);
                    hourEt.setVisibility(View.GONE);
                }
            }
        });

    }

    @OnClick(R.id.get_data_btn)
    public void onViewClicked() {
        KeyboardUtils.hideSoftInput(this);

        if (mDataType == 4) {
            mPresenter.queryHoroscope(dateEt.getText().toString(), hourEt.getText().toString());
        } else if(mDataType == 5) {
            mPresenter.queryPerpetual(dateEt.getText().toString());
        }else {
            mInput = inputEt.getText().toString();
            if (TextUtils.isEmpty(mInput)) {
                showMessage("输入内容不能为空");
                return;
            }
            if (mDataType == 1) {
                mPresenter.queryDictionary(mInput);
            } else if (mDataType == 2) {
                mPresenter.queryIdiom(mInput);
            } else if (mDataType == 3) {
                mPresenter.queryHistory(mInput);
            }
        }

    }

    @Override
    protected void onErrorAndEmptyAction() {
        super.onErrorAndEmptyAction();
        onViewClicked();
    }

    @Override
    public void showHistory(List<HistoryEntity> historyEntityList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (null != historyEntityList && !historyEntityList.isEmpty()) {
            for (HistoryEntity entity : historyEntityList) {
                stringBuilder.append(entity.getEvent()).append("\n");
            }
        }
        resultTv.setText(stringBuilder.toString());
    }

    @Override
    public void showDictionary(DictionaryEntity dictionaryEntity) {
        resultTv.setText(dictionaryEntity.toString());
    }

    @Override
    public void showIdiom(IdiomEntity idiomEntity) {
        resultTv.setText(idiomEntity.toString());
    }

    @Override
    public void showHoroscope(HoroscopeEntity horoscopeEntity) {
        resultTv.setText(horoscopeEntity.toString());
    }

    @Override
    public void showPerpetual(PerpetualCalendar perpetualCalendar) {
        resultTv.setText(perpetualCalendar.toString());
    }

}

