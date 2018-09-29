package com.dms.base.baseapplication.ui.fragment;


import android.widget.EditText;
import com.dms.base.baseapplication.R;
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

    @OnClick(R.id.submit_btn)
    public void onViewClicked() {
        mPresenter.editUserInfo(userNameEt.getText().toString(), userGenderEt.getText().toString(), userAgeEt.getText().toString());
    }

    public void onEditUserInfoSuccess() {
        showMessage("修改用户信息成功");
    }
}
