package com.example.demoadmob.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoadmob.R;
import com.example.demoadmob.template.NativeTemplateStyle;
import com.example.demoadmob.template.TemplateView;
import com.google.android.gms.ads.nativead.NativeAd;

public class AdViewHolder extends RecyclerView.ViewHolder {

    public TemplateView templateView;

    public AdViewHolder(@NonNull View itemView) {
        super(itemView);

        templateView = itemView.findViewById(R.id.ad_template_view);

        NativeTemplateStyle styles = new
                NativeTemplateStyle.Builder().build();
        templateView.setStyles(styles);

    }

    public void setNativeAd(NativeAd nativeAd){
        templateView.setNativeAd(nativeAd);
    }
}
