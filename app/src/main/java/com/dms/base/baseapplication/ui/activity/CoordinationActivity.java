package com.dms.base.baseapplication.ui.activity;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.img.ImgLoadHelper;
import com.dms.base.baseapplication.ui.fragment.HealthFragment;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CoordinationActivity extends BaseActivity {
    @BindView(R.id.header_iv)
    ImageView headerIv;
    @BindView(R.id.content_vp)
    ViewPager contentVp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coordination;
    }


    @Override
    public void initView(View rootView) {
        super.initView(rootView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        ImgLoadHelper.loadImg(this, "https://p1.ssl.qhimgs1.com/t010b830690f9740a51.jpg", headerIv);
        final List<HealthFragment> healthFragments = new ArrayList<>(3);
        healthFragments.add(HealthFragment.newFragment("感冒"));
        healthFragments.add(HealthFragment.newFragment("发烧"));
        healthFragments.add(HealthFragment.newFragment("鼻炎"));

        contentVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return healthFragments.get(i);
            }

            @Override
            public int getCount() {
                return healthFragments.size();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(NetError netError) {

    }

}
