package com.dms.base.baseproject.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.dms.base.baseproject.R;

public class LoadingDialog extends Dialog {

    TextView loadingTv;

    public void setLoadingTxt(String loadingTxt) {
        if(null != loadingTv) {
            loadingTv.setText(loadingTxt);
        }
    }

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_loading);
        loadingTv = findViewById(R.id.loading_tv);
    }

}
