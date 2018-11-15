package com.dms.base.baseapplication.ui.adapter;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dms.base.baseapplication.R;

import java.util.List;

public class BluetoothDeviceAdapter extends BaseQuickAdapter<BluetoothDevice, BaseViewHolder> {
    public BluetoothDeviceAdapter(int layoutResId, @Nullable List<BluetoothDevice> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BluetoothDevice item) {
        helper.setText(R.id.single_select_item_tv, item.getAddress());
    }
}
