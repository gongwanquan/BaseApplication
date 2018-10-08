package com.dms.base.baseapplication.ui.fragment;

import android.text.TextUtils;
import android.widget.EditText;
import com.blankj.utilcode.util.EncryptUtils;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.net.ApiManager;
import com.dms.base.baseproject.mvp.presenter.BasePresenter;
import com.dms.base.baseproject.net.ResponseListener;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.fragment.BaseUiFragment;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangePasswordFragment extends BaseUiFragment<BasePresenter> {
    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.old_password_et)
    EditText oldPasswordEt;
    @BindView(R.id.new_password_et)
    EditText newPasswordEt;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_change_password;
    }

    @OnClick(R.id.submit_btn)
    public void onViewClicked() {
        changePassword();
    }

    private void changePassword() {
        String username = usernameEt.getText().toString();
        String oldPassword = oldPasswordEt.getText().toString();
        String newPassword = newPasswordEt.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword)) {
            showMessage("username or password cannot be empty!");
            return;
        }

        oldPassword = EncryptUtils.encryptMD5ToString(oldPassword);
        newPassword = EncryptUtils.encryptMD5ToString(newPassword);

        mPresenter.subscribe(ApiManager.getUserService().changePassword(username, oldPassword, newPassword), new ResponseListener() {
            @Override
            public void onSuccess(Object o) {
                showMessage("change password success");
            }

            @Override
            public boolean handleError(NetError netError) {
                return false;
            }
        });


    }
}
