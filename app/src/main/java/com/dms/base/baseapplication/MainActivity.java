package com.dms.base.baseapplication;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

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
    }


    @OnClick({R.id.history_btn, R.id.dictionary_btn, R.id.idiom_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.history_btn:
                List<Fragment> fragments = FragmentUtils.getFragments(getSupportFragmentManager());
                ToastUtils.showShort("fragment num = " + fragments.size());
                break;
            case R.id.dictionary_btn:
                FragmentUtils.replace(getSupportFragmentManager(),
                        DictionaryFragment.newInstance(inputEt.getText().toString()),
                        R.id.result_container_fl);
                break;
            case R.id.idiom_btn:
                break;
        }
    }
}

