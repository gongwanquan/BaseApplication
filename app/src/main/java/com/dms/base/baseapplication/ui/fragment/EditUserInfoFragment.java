package com.dms.base.baseapplication.ui.fragment;


import android.os.Bundle;
import android.widget.EditText;

import com.blankj.utilcode.util.CacheDoubleUtils;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.entity.UserEntity;
import com.dms.base.baseapplication.mvp.presenter.EditUserInfoPresenter;
import com.dms.base.baseproject.ui.fragment.BaseUiFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class EditUserInfoFragment extends BaseUiFragment<EditUserInfoPresenter> {
    @BindView(R.id.user_name_et)
    EditText userNameEt;
    @BindView(R.id.user_gender_et)
    EditText userGenderEt;
    @BindView(R.id.user_age_et)
    EditText userAgeEt;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_edit_user_info;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mPresenter.queryUserInfo();
    }

    public void showUserInfo(UserEntity.UserInfo userInfo) {
        userNameEt.setText(userInfo.getName());
        userGenderEt.setText(userInfo.getSex() == 0 ? "男" : "女");
        userAgeEt.setText(String.valueOf(userInfo.getAge()));
    }

    @OnClick(R.id.submit_btn)
    public void onViewClicked() {
        mPresenter.editUserInfo(userNameEt.getText().toString(), userGenderEt.getText().toString(), userAgeEt.getText().toString());
    }

    public void onEditUserInfoSuccess() {
        showMessage("修改用户信息成功");
    }
}
