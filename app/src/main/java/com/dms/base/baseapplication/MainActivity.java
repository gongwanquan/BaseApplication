package com.dms.base.baseapplication;

import android.widget.TextView;
import com.blankj.utilcode.util.ActivityUtils;
import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseUIActivity<FuckPresenter> implements FuckView {


    @BindView(R.id.history_tv)
    TextView historyTv;

    @Override
    public void fuck(String str) {
        historyTv.setText(str);
        ActivityUtils.startActivity(AnotherActivity.class);
    }


    @Override
    public FuckPresenter createPresenter() {
        return new FuckPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        historyTv.setText("你好");
    }

    @OnClick(R.id.history_tv)
    public void onViewClicked() {
        mPresenter.getHistory();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
