package com.dms.base.baseapplication.ui.widget;

import android.view.View;
import android.widget.TextView;

import com.dms.base.baseapplication.R;
import com.dms.base.baseproject.ui.dialog.BaseDialog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;


public class LoadingDialog extends BaseDialog {

    @BindView(R.id.loading_tv)
    TextView loadingTv;


    @Override
    public int getLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    public void initView(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().setCancelable(false);
        }

        Observable.just(new Object())
                .delay(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (getDialog() != null && getDialog().isShowing()) {
                            getDialog().setCanceledOnTouchOutside(true);
                        }
                    }
                });

    }

    public void setLoadingTxt(String loadingTxt) {
        loadingTv.setText(loadingTxt);
    }
}
