package com.sye.base.fragments.blue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sye.base.BaseFragment;
import com.sye.base.R;
import com.sye.base.network.RestEvent;
import com.sye.base.network.Endpoint;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BlueFragment extends BaseFragment implements BlueContract.View {

    private BlueContract.Presenter presenter;

    public BlueFragment() {
    }

    public static BlueFragment newInstance() {

        Bundle args = new Bundle();

        BlueFragment fragment = new BlueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.btn_new).setOnClickListener(view -> getCallback().sendBack(getTag()));

        presenter.fetchData();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BluePresenter(this);
        presenter.create();
    }

    @Override
    public void showData() {
        progress(false);
    }

}
