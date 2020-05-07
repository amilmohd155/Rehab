/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/13/20 11:33 PM
 *
 */

package com.example.drugaddictioncounsellingapp.home.binder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drugaddictioncounsellingapp.R;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class HeaderItemBinder extends ItemBinder<Integer, HeaderItemBinder.HViewHolder> {
    @Override
    public HViewHolder createViewHolder(ViewGroup parent) {
        return new HViewHolder(inflate(parent, R.layout.layout_header));
    }

    @Override
    public void bindViewHolder(HViewHolder holder, Integer item) {
        holder.tvHeader.setText(item);
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof Integer;
    }

    static class HViewHolder extends ItemViewHolder<Integer> {

        TextView tvHeader;

        public HViewHolder(View itemView) {
            super(itemView);

            tvHeader = itemView.findViewById(R.id.tv_header);

        }
    }
}
