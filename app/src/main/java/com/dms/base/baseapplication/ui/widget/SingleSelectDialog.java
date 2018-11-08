package com.dms.base.baseapplication.ui.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dms.base.baseapplication.R;
import com.dms.base.baseproject.ui.dialog.BaseDialog;

import java.util.ArrayList;

public class SingleSelectDialog extends BaseDialog {

    private ListView mSelectItemLv;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_single_select;
    }

    @Override
    public void initView(View v) {

        mSelectItemLv = v.findViewById(R.id.single_select_item_lv);
        mSelectItemLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        v.findViewById(R.id.single_select_cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getContext(), R.layout.item_single_select, R.id.single_select_item_tv, getData());
        mSelectItemLv.setAdapter(arrayAdapter);
    }

    private ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();

        list.add("龚万全");
        list.add("龚波");
        list.add("龚慧明");
        list.add("钟立新");
        list.add("钟立红");
        list.add("方平");
        list.add("方娅");

        return list;
    }
}
