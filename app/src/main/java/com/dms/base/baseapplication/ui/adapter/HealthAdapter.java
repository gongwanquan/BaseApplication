package com.dms.base.baseapplication.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dms.base.baseapplication.R;
import com.dms.base.baseapplication.model.HealthEntity;

import java.util.List;

public class HealthAdapter extends BaseQuickAdapter<HealthEntity, BaseViewHolder> {

    public HealthAdapter(int layoutResId, @Nullable List<HealthEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthEntity item) {
        helper.setText(R.id.title_tv, (helper.getAdapterPosition() + 1) + "，" + item.getTitle());
        helper.setText(R.id.content_tv, item.getContent());
    }


}
