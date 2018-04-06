package com.sye.base.fragments.blue;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sye.base.R;

import java.util.List;

public class BlueAdapter extends RecyclerView.Adapter<BlueAdapter.BlueViewHolder> {


    private final List<BlueObject> list;

    BlueAdapter(List<BlueObject> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BlueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_blue_items, parent, false);

        return new BlueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BlueViewHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BlueViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewId;

        BlueViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewId = itemView.findViewById(R.id.textViewId);
        }

        void bindData(BlueObject item) {
            textViewName.setText(item.getName());
            textViewId.setText(item.getId());
        }
    }
}
