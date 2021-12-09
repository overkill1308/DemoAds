package com.example.demoadmob.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoadmob.R;
import com.example.demoadmob.model.ItemModel;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int IS_AD = 0;
    private static final int NOT_AD = 1;

    private final ArrayList<Object> objects = new ArrayList<>();

    public void setList(List<ItemModel> itemList){
        this.objects.addAll(itemList);
    }

    public void setAd(List<NativeAd> nativeAd){
        this.objects.addAll(nativeAd);
        notifyDataSetChanged();
    }

    public void setObject(ArrayList<Object> objects){
        this.objects.clear();
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == IS_AD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ad, parent, false);
            return new AdViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == IS_AD){
            AdViewHolder adViewHolder = (AdViewHolder) holder;
            adViewHolder.setNativeAd((NativeAd) objects.get(position));
        } else {
            ItemModel itemModel = (ItemModel) objects.get(position);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.tv_item.setText(itemModel.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (objects.get(position) instanceof NativeAd){
            return IS_AD;
        } else {
            return NOT_AD;
        }
    }
}
