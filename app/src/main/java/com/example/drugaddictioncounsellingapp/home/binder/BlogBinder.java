/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 7:56 PM
 *
 */

package com.example.drugaddictioncounsellingapp.home.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.LayoutBlogBinding;
import com.example.drugaddictioncounsellingapp.home.model.Blog;


import mva2.extension.DBItemBinder;

public class BlogBinder extends DBItemBinder<Blog, LayoutBlogBinding> {

    private static final String TAG = "BlogBinder";

    private BinderListener listener;

    public BlogBinder(BinderListener listener) {
        this.listener = listener;
    }

    @Override
    protected void bindModel(Blog blog, LayoutBlogBinding binding) {
        binding.setBlog(blog);
        binding.getRoot().setOnClickListener(view -> {
            listener.onBlogClicked(blog);
        });
    }

    @Override
    protected LayoutBlogBinding createBinding(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return DataBindingUtil.inflate(inflater, R.layout.layout_blog, parent, false);
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof Blog;
    }

    public interface BinderListener {
        void onBlogClicked(Blog blog);
    }

}
