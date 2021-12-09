package com.example.demoadmob.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoadmob.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_item;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_item = itemView.findViewById(R.id.tv_item);
    }
}
