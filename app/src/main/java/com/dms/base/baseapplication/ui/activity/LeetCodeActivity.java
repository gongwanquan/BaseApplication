package com.dms.base.baseapplication.ui.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.dms.base.baseapplication.R;
import com.dms.base.baseproject.ui.activity.BaseUIActivity;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LeetCodeActivity extends BaseUIActivity {
    @BindView(R.id.swipe_layout)
    SwipeMenuLayout mSwipeMenuLayout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_leet_code;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
//        mSwipeMenuLayout.setSwipeEnable(false);
    }

    private void twoSum() {
        int nums[] = {2, 7, 11, 15};
        int target = 17;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
//                resultTv.setText("key1 = " + map.get(target - nums[i]) + "\nkey2 = " + i);
                break;
            }
            map.put(nums[i], i);
        }
    }

    private void reverseInteger() {
        int source = -14646123;
        int result = 0;
        while (Math.abs(source) > 0) {
            result = result * 10 + source % 10;
            source = source / 10;
        }


    }
}
