package com.dms.base.baseapplication.ui.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.FragmentUtils;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.ui.fragment.DictionaryFragment;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;


public class AnotherActivity extends BaseUIActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_another;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setTitle("Fragment测试");
        FragmentUtils.add(getSupportFragmentManager(), DictionaryFragment.createInstance("龚"), R.id.root_fl);
    }

}
