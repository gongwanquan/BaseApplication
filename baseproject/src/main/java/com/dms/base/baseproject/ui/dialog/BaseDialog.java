package com.dms.base.baseproject.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null, false);
        ButterKnife.bind(rootView);
        initView(rootView);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(rootView);

        return builder.create();
    }

    abstract public int getLayoutId();

    abstract public void initView(View v);
}
