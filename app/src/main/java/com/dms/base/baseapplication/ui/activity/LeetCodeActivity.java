package com.dms.base.baseapplication.ui.activity;


import android.os.Bundle;
import android.view.View;

import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.ui.widget.FingerprintDialog;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.activity.BaseActivity;

import butterknife.OnClick;


public class LeetCodeActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_leet_code;
    }


    @Override
    public void initView(View rootView) {
        super.initView(rootView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        Fragment biyan = HealthFragment.newFragment("鼻炎");
//        FragmentUtils.add(getSupportFragmentManager(), biyan, R.id.root_layout, false, true);
//        Fragment ganmao =  HealthFragment.newFragment("感冒");
//        FragmentUtils.replace(biyan, ganmao, "", true);
//        Fragment fasao = HealthFragment.newFragment("发烧");
//        FragmentUtils.replace(ganmao, fasao, "", true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(NetError netError) {

    }

    @OnClick(R.id.finger_print_btn)
    public void onViewClicked() {
        FingerprintDialog dialog = new FingerprintDialog();
        dialog.show(getSupportFragmentManager(), "");
    }
}
