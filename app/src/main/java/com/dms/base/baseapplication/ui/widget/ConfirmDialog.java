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
import android.widget.TextView;

import com.dms.base.baseapplication.R;

public class ConfirmDialog extends Dialog implements View.OnClickListener {
    private TextView mTitleTv;

    private TextView mContentTv;

    private String mTitleStr;

    private String mContentStr;

    private ConfirmListener mConfirmListener;

    public void setTitleStr(String titleStr) {
        mTitleStr = titleStr;
    }

    public void setContentStr(String contentStr) {
        mContentStr = contentStr;
    }

    public void setConfirmListener(ConfirmListener confirmListener) {
        mConfirmListener = confirmListener;
    }

    public ConfirmDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_confirm);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        mTitleTv = findViewById(R.id.title_tv);
        mContentTv = findViewById(R.id.content_tv);
        findViewById(R.id.confirm_tv).setOnClickListener(this);
        findViewById(R.id.cancel_tv).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!TextUtils.isEmpty(mTitleStr)) {
            mTitleTv.setText(mTitleStr);
        }

        if(!TextUtils.isEmpty(mContentStr)) {
            mContentTv.setText(mContentStr);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.confirm_tv) {
            if(null != mConfirmListener) {
                mConfirmListener.onConfirm();
            }
            dismiss();
        } else if(v.getId() == R.id.cancel_tv) {
            dismiss();
        }
    }

    public interface ConfirmListener {
        void onConfirm();
    }
}
