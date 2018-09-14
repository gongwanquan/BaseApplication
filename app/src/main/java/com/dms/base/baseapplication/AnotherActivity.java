package com.dms.base.baseapplication;


import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dms.base.baseproject.mvp.IPresenter;

import butterknife.BindView;

public class AnotherActivity extends BaseUIActivity {

    @BindView(R.id.circle_pb)
    CircleProgress mCircleProgress;

    float value = 0.f;

    boolean onProgress = false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(value >= mCircleProgress.getMaxValue()) {
               stopProgress();
                ToastUtils.showShort("百分百");
                return;
            }
            value += 0.01;
            mCircleProgress.setValue(value);
            mHandler.sendEmptyMessageDelayed(0, 10);
        }

    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_another;
    }

    @Override
    public IPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
    }


    public void changeProgress(View view) {
//        if(!onProgress) {
//            startProgress();
//        } else {
//            stopProgress();
//        }
    }

    private void startProgress() {
        onProgress = true;
        value = 0;
        mCircleProgress.setValue(value);
        mHandler.sendEmptyMessageDelayed(0, 10);
    }

    private void stopProgress() {
        onProgress = false;
        mHandler.removeMessages(0);
    }
}
