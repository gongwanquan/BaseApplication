package com.dms.base.baseapplication.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.CacheDoubleUtils;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.entity.BaseResponse;
import com.dms.base.baseapplication.entity.UserEntity;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class UserActivity extends BaseUIActivity<BasePresenter> {

    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.result_tv)
    TextView resultTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.register_btn)
    public void onViewClicked() {
//        mPresenter.login(usernameEt.getText().toString(), passwordEt.getText().toString());
        login();
    }

    private void login() {
        mPresenter.subscribe(ApiManager.getUserService().login(usernameEt.getText().toString(), passwordEt.getText().toString()), new ResponseListener<BaseResponse<UserEntity>>() {
            @Override
            public void onSuccess(BaseResponse<UserEntity> userEntityBaseResponse) {
                CacheDoubleUtils.getInstance().put("login_user", userEntityBaseResponse.getData());
                ActivityUtils.startActivity(AnotherActivity.class);
            }

            @Override
            public boolean handleError(NetError netError) {
                showMessage(netError.getMessage());
                return true;
            }
        });
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setTitle(R.string.title_activity_login);
    }

    @Override
    protected void onErrorAndEmptyAction() {
        super.onErrorAndEmptyAction();
//        mPresenter.login(usernameEt.getText().toString(), passwordEt.getText().toString());
    }


    public void onRegisterSuccess(String uid) {
        resultTv.setText("用户ID：" + uid);
    }


    public void onLoginSuccess(UserEntity userEntity) {
        CacheDoubleUtils.getInstance().put("login_user", userEntity);
        ActivityUtils.startActivity(AnotherActivity.class);
    }
}
