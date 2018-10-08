package com.dms.base.baseapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;
import android.widget.TableLayout;

import com.blankj.utilcode.util.FragmentUtils;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.ui.fragment.ChangePasswordFragment;
import com.dms.base.baseapplication.ui.fragment.EditUserInfoFragment;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;
import com.dms.base.baseproject.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class AnotherActivity extends BaseUIActivity {

    @BindView(R.id.root_vp)
    ViewPager rootVp;

    private List<BaseFragment> mBaseFragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_another;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setTitle("编辑用户");

        mBaseFragments = new ArrayList<>(2);
        mBaseFragments.add(new EditUserInfoFragment());
        mBaseFragments.add(new ChangePasswordFragment());

        rootVp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mBaseFragments.get(position);
            }

            @Override
            public int getCount() {
                return mBaseFragments.size();
            }
        });

        rootVp.setOffscreenPageLimit(2);
        rootVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i == 0) {
                    mTitleBar.setTitle("编辑用户");
                } else if(i == 1) {
                    mTitleBar.setTitle("修改密码");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


}
