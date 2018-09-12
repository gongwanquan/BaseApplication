package com.dms.base.baseapplication;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;


public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context);

        setContentView(R.layout.dialog_loading);

        setCanceledOnTouchOutside(false);
    }

}
