package com.dms.base.baseapplication;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.blankj.rxbus.RxBus;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseUIActivity {

    @BindView(R.id.input_et)
    EditText inputEt;


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
        RxBus.getDefault().subscribe(this, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                mTitleBar.setTitle(s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }

    @OnClick({R.id.history_btn, R.id.dictionary_btn, R.id.idiom_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.history_btn:
                ActivityUtils.startActivity(AnotherActivity.class);
                break;
            case R.id.dictionary_btn:
                FragmentUtils.replace(getSupportFragmentManager(),
                        DictionaryFragment.createInstance(inputEt.getText().toString()),
                        R.id.result_container_fl);
                break;
            case R.id.idiom_btn:
                FragmentUtils.add(getSupportFragmentManager(),
                        DictionaryFragment.createInstance("龚"),
                        R.id.result_container_fl);
                break;
        }
    }
}

