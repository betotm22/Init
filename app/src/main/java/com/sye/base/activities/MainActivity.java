package com.sye.base.activities;

import android.os.Bundle;

import com.sye.base.BaseActivity;
import com.sye.base.R;
import com.sye.base.fragments.green.GreenFragment;
import com.sye.base.fragments.blue.BlueFragment;

public class MainActivity extends BaseActivity {

    private String TAG_BLUE;
    private String TAG_GREEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TAG_BLUE = addConstantFragment(BlueFragment.newInstance(), this);
    }

    @Override
    public void sendBack(String tag) {
        super.sendBack();

        if (tag.equals(TAG_BLUE)) {
            TAG_GREEN = addFragment(GreenFragment.newInstance(), R.id.main_fragment_container, this);

        } else if (tag.equals(TAG_GREEN)) {
            remove(TAG_GREEN);
        }

    }
}
