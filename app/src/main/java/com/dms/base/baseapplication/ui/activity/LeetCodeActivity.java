package com.dms.base.baseapplication.ui.activity;


import android.view.View;
import com.dms.base.baseapplication.R;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;
import butterknife.OnClick;

public class LeetCodeActivity extends BaseUIActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_leet_code;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);

        mTitleBar.setTitle("测试");

    }

    @OnClick(R.id.show_dialog_btn)
    public void onViewClicked() {

    }
}
