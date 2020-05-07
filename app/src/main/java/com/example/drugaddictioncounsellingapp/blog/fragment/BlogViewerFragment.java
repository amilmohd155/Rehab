/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/18/20 9:53 PM
 *
 */

package com.example.drugaddictioncounsellingapp.blog.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.FragmentBlogViewerBinding;
import com.example.drugaddictioncounsellingapp.home.model.Blog;

public class BlogViewerFragment extends Fragment{

    private static final String TAG = "BlogViewerFragment";
    private static final String BLOG_KEY = "Blog";

    private Blog blog;

    public static BlogViewerFragment newInstance(Blog blog) {

        Bundle args = new Bundle();

        args.putParcelable(BLOG_KEY, blog);

        BlogViewerFragment fragment = new BlogViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            blog = getArguments().getParcelable(BLOG_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: blogID@" + blog.getBlogID());

        FragmentBlogViewerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blog_viewer, container, false);
        binding.setBlog(blog);
        binding.setFragment(this);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        blog = null;
    }
}
