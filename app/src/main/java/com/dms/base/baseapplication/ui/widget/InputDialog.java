package com.dms.base.baseapplication.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dms.base.baseapplication.R;

public class InputDialog extends Dialog implements View.OnClickListener {
    private TextView mTitleTv;

    private EditText mInputEt;

    private String mTitleStr;

    private ConfirmListener mConfirmListener;

    public void setTitleStr(String titleStr) {
        mTitleStr = titleStr;
    }

    public void setConfirmListener(ConfirmListener confirmListener) {
        mConfirmListener = confirmListener;
    }

    public InputDialog(@NonNull Context context) {
        super(context);

        setContentView(R.layout.dialog_input);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitleTv = findViewById(R.id.title_tv);
        mInputEt = findViewById(R.id.input_et);
        findViewById(R.id.confirm_tv).setOnClickListener(this);
        findViewById(R.id.cancel_tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.confirm_tv) {
            if(null != mConfirmListener) {
                mConfirmListener.onConfirm(mInputEt.getText().toString());
            }
            dismiss();
        } else if(v.getId() == R.id.cancel_tv) {
            dismiss();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!TextUtils.isEmpty(mTitleStr)) {
            mTitleTv.setText(mTitleStr);
        }
    }

    public interface ConfirmListener {
        void onConfirm(String inputStr);
    }
}
