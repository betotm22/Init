package com.sye.base.fragments.green;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sye.base.BaseFragment;
import com.sye.base.R;

public class GreenFragment extends BaseFragment {

    public GreenFragment() {
    }

    public static GreenFragment newInstance() {

        Bundle args = new Bundle();

        GreenFragment fragment = new GreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_green, container, false);

        root.findViewById(R.id.btn_close).setOnClickListener(view -> getCallback().sendBack(getTag()));

        return root;
    }
}
