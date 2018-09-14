package com.dms.base.baseapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseUIActivity<MobPresenter> implements MobView {


    @BindView(R.id.input_et)
    EditText inputEt;
    @BindView(R.id.result_tv)
    TextView resultTv;

    @Override
    public void showIdiom(IdiomEntity idiomEntity) {
        resultTv.setText(idiomEntity.toString());
    }

    @Override
    public void showDictionary(DictionaryEntity dictionaryEntity) {
        resultTv.setText(dictionaryEntity.toString());
    }

    @Override
    public void showHoroscope(HoroscopeEntity horoscopeEntity) {
        resultTv.setText(horoscopeEntity.toString());
    }


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
        mTitleBar.setTitle("八字算命");
    }

    @OnClick(R.id.submit_btn)
    public void onViewClicked() {
//        String inputStr = inputEt.getText().toString();
//        if(!StringUtils.isEmpty(inputStr)) {
//            mPresenter.queryDictionary(inputStr);
//        }




        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    final StringBuilder dateSb = new StringBuilder();
                    dateSb.append(year).append("-").append(month + 1).append("-").append(dayOfMonth);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            mPresenter.horoscope(dateSb.toString(), String.valueOf(hourOfDay));
                            inputEt.setText(dateSb.toString() + " :" + String.valueOf(hourOfDay));
                        }
                    }, 17, 3, true);

                    timePickerDialog.show();
                }
            }, 1989,0,22);
            datePickerDialog.show();
        }
    }
}
