package com.sye.base.fragments.blue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sye.base.bases.BaseFragment;
import com.sye.base.R;

import java.util.List;

public class BlueFragment extends BaseFragment implements BlueContract.View {

    private BlueContract.Presenter presenter;

    private RecyclerView recyclerView;

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
        View root = inflater.inflate(R.layout.fragment_blue, container, false);

        root.findViewById(R.id.btn_new).setOnClickListener(view -> getCallback().sendBack(getTag()));

        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
    public void showData(List<BlueObject> list) {
        BlueAdapter adapter = new BlueAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
