/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 5:40 PM
 *
 */

package com.example.drugaddictioncounsellingapp.utils;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.drugaddictioncounsellingapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class BindingConfig {

    private static final String TAG = "BindingConfig";

    @BindingAdapter("error")
    public static void setErrorText(TextInputLayout view, String errorText) {
        view.setError(errorText);
    }

    @BindingAdapter("onNavigationIconClicked")
    public static void onNavigationIconClicked(Toolbar toolbar, Fragment fragment) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.getActivity().onBackPressed();
            }
        });
    }

    @BindingAdapter(value = {"imageUrl", "imageCornerRadius"}, requireAll = true)
    public static void loadImage(ImageView view, String imageUrl, int radius) {

        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .fallback(R.drawable.loading)
                .transform(new CenterCrop(), new RoundedCorners(radius))
                .into(view);
    }

    @BindingAdapter(value = {"profileUrl", "size"}, requireAll = false)
    public static void setProfileImage(ImageView view, String imageUrl, float size) {

        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .fallback(R.drawable.avatar)
                .override((int)size, (int)size)
                .centerCrop()
                .into(view);
    }

}
