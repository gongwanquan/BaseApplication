package com.dms.base.baseapplication.ui.activity;


import android.widget.ImageView;

import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.ui.widget.SingleSelectDialog;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LeetCodeActivity extends BaseUIActivity {


    @BindView(R.id.result_iv)
    ImageView resultIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_leet_code;
    }


    @OnClick(R.id.get_photo_btn)
    public void onViewClicked() {
//        GlideApp.with(this)
//                .load("https://p2.ssl.qhimgs1.com/sdr/200_200_/t013eb47e70063fa4e2.jpg")
//                .override(500)
//                .into(resultIv);

//        ConfirmDialog confirmDialog = new ConfirmDialog(this);
//        confirmDialog.setTitleStr("升级");
//        confirmDialog.setContentStr("当前最新版本为2.3.0，是否升级？");
//        confirmDialog.setConfirmListener(new ConfirmDialog.ConfirmListener() {
//            @Override
//            public void onConfirm() {
//                showMessage("升级为2.3版本");
//            }
//        });
//        confirmDialog.show();

        SingleSelectDialog singleSelectDialog = new SingleSelectDialog();
        singleSelectDialog.show(getSupportFragmentManager(), "");
    }
}
