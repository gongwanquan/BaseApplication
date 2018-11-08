package com.dms.base.baseapplication.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.view.View;
import android.widget.TextView;

import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.ui.activity.LeetCodeActivity;
import com.dms.base.baseproject.ui.dialog.BaseDialog;

import javax.crypto.Cipher;

public class FingerprintDialog extends BaseDialog {
    private FingerprintManagerCompat fingerprintManager;

    private CancellationSignal mCancellationSignal;

    private Cipher mCipher;

    private LeetCodeActivity mActivity;

    private TextView errorMsg;
    /**
     * 标识是否是用户主动取消的认证。
     */
    private boolean isSelfCancelled;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_fingerprint;
    }

    @Override
    public void initView(View v) {
        errorMsg = v.findViewById(R.id.error_msg);
        TextView cancel = v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                stopListening();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }



    public void setCipher(Cipher cipher) {
        mCipher = cipher;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (LeetCodeActivity) getActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
        fingerprintManager = FingerprintManagerCompat.from(getContext());
        if(!fingerprintManager.isHardwareDetected()) {
            showMessage("指纹识别硬件不存在！");
        }

        if(!fingerprintManager.hasEnrolledFingerprints()) {
            showMessage("未录入过指纹！");
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        // 开始指纹认证监听
        startListening(mCipher);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 停止指纹认证监听
        stopListening();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startListening(Cipher cipher) {
        isSelfCancelled = false;
        mCancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(new FingerprintManagerCompat.CryptoObject(cipher), 0, mCancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                super.onAuthenticationError(errMsgId, errString);
                if (!isSelfCancelled) {
                    errorMsg.setText(errString);
                    dismiss();
                }

            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                super.onAuthenticationHelp(helpMsgId, helpString);
                errorMsg.setText(helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                showMessage("指纹认证成功");
                dismiss();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                errorMsg.setText("指纹认证失败，请再试一次");
            }
        }, null);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void stopListening() {
        if (mCancellationSignal != null) {
            mCancellationSignal.cancel();
            mCancellationSignal = null;
            isSelfCancelled = true;
        }
    }

}
