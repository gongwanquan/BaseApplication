package com.dms.base.baseapplication;


import android.widget.ImageView;

import com.dms.base.baseproject.img.ILoader;
import com.dms.base.baseproject.img.LoaderFactory;
import com.dms.base.baseproject.mvp.IPresenter;

import butterknife.BindView;
import butterknife.OnClick;


public class AnotherActivity extends BaseUIActivity {


    @BindView(R.id.net_img_iv)
    ImageView netImgIv;

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


    @OnClick(R.id.load_img_btn)
    public void onViewClicked() {
        ImgLoaderManager.loadCircleImage(this, "http://file06.16sucai.com/2016/0513/42689f41a6d04aed7f28aeecbb029d41.jpg", netImgIv);
    }
}
