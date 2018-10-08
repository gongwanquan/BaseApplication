package com.dms.base.baseapplication.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.CacheDoubleUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.entity.BaseResponse;
import com.dms.base.baseapplication.entity.UserEntity;
import com.dms.base.baseapplication.img.ImgLoadHelper;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseproject.img.LoaderFactory;
import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;
import com.dms.base.baseproject.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.OnClick;

public class UserActivity extends BaseUIActivity<BasePresenter> {

    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.scenery_iv)
    ImageView sceneryIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.register_btn)
    public void onViewClicked() {
//        mPresenter.login(usernameEt.getText().toString(), passwordEt.getText().toString());
        login();

        ImgLoadHelper.loadCircleImage(this, "https://p2.ssl.qhimgs1.com/bdr/200_200_/t01b55948489accbdd2.jpg", sceneryIv);
    }

    private void login() {
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            showMessage("用户名或者密码无能为空");
            return;
        }
        password = EncryptUtils.encryptMD5ToString(password);
        mPresenter.subscribe(ApiManager.getUserService().login(username, password), new ResponseListener<BaseResponse<UserEntity>>() {
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

    }


    public void onLoginSuccess(UserEntity userEntity) {
        CacheDoubleUtils.getInstance().put("login_user", userEntity);
        ActivityUtils.startActivity(AnotherActivity.class);
    }
}
