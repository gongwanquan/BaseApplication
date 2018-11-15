package com.dms.base.baseapplication.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.rxbus.RxBus;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.model.HealthEntity;
import com.dms.base.baseapplication.model.PageEntity;
import com.dms.base.baseapplication.mvp.presenter.HealthPresenter;
import com.dms.base.baseapplication.mvp.view.HealthView;
import com.dms.base.baseapplication.ui.adapter.HealthAdapter;
import com.dms.base.baseproject.net.error.NetError;
import com.dms.base.baseproject.ui.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class HealthFragment extends BaseFragment<HealthPresenter> implements HealthView {

    @BindView(R.id.result_rv)
    RecyclerView resultRv;

    private String mKeywords;

    private List<HealthEntity> mData;

    private HealthAdapter mAdapter;

    public static HealthFragment newFragment(String keywords) {
        HealthFragment fragment = new HealthFragment();
        Bundle bundle = new Bundle();
        bundle.putString("extra_keywords", keywords);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_health;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        resultRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle bundle = getArguments();
        mKeywords = bundle.getString("extra_keywords");

        mAdapter = new HealthAdapter(R.layout.item_health, mData);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.searchHealth(false, mKeywords);
            }
        }, resultRv);
        resultRv.setAdapter(mAdapter);

        onRefresh();
    }

    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        mPresenter.searchHealth(true, mKeywords);
    }

    @Override
    public void showData(boolean isRefresh, PageEntity<HealthEntity> healthEntityPageEntity) {
        if(isRefresh) {
            RxBus.getDefault().post(new Object());
            mAdapter.setEnableLoadMore(true);
            mAdapter.setNewData(healthEntityPageEntity.getList());
        } else {
            mAdapter.addData(healthEntityPageEntity.getList());
            if(mAdapter.getData().size() == healthEntityPageEntity.getTotal()) {
                mAdapter.loadMoreEnd();
            } else {
                mAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void loadDataFail(boolean isRefresh) {
        if(isRefresh) {
            mAdapter.setEnableLoadMore(true);
        } else {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(NetError netError) {
        showMessage(netError.getMessage());
    }


}
