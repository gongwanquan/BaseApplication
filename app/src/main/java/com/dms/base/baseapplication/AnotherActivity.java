package com.dms.base.baseapplication;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dms.base.baseproject.imageloader.LoaderFactory;
import com.dms.base.baseproject.mvp.IPresenter;

import butterknife.BindView;

public class AnotherActivity extends BaseUIActivity {

    @BindView(R.id.net_iv)
    ImageView netIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_another;
    }

    @Override
    public IPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    public void loadImage(View view) {
        Glide.with(this).load("http://pic24.nipic.com/20121029/3822951_123134776000_2.jpg")
                .transition(GenericTransitionOptions.<Drawable>with(android.R.anim.slide_in_left))
                .into(netIv);
    }
}
