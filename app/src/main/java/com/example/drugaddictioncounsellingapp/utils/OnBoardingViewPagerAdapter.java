/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/9/20 3:20 PM
 *
 */

package com.example.drugaddictioncounsellingapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.drugaddictioncounsellingapp.databinding.LayoutOnBoardingScreenBinding;

import java.util.List;

public class OnBoardingViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ScreenItem> screenItems;

    public OnBoardingViewPagerAdapter(Context context, List<ScreenItem> screenItems) {
        this.context = context;
        this.screenItems = screenItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LayoutOnBoardingScreenBinding binding = LayoutOnBoardingScreenBinding.inflate(inflater, container, false);

        Glide.with(context)
                .load(context.getDrawable(screenItems.get(position).getOnBoardingImage()))
                .into(binding.onBoardingImage);

        binding.onBoardingText.setText(screenItems.get(position).getOnBoardingText());

        return binding.getRoot();

    }

    @Override
    public int getCount() {
        return screenItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);

        container.removeView((View) object);

    }
}
