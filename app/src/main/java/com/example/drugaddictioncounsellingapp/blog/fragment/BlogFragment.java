/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/8/20 11:37 PM
 *
 */

package com.example.drugaddictioncounsellingapp.blog.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.blog.BlogViewModel;
import com.example.drugaddictioncounsellingapp.blog.CreateBlog;
import com.example.drugaddictioncounsellingapp.databinding.FragmentBlogBinding;
import com.example.drugaddictioncounsellingapp.home.binder.BlogBinder;
import com.example.drugaddictioncounsellingapp.home.model.Blog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

import static com.example.drugaddictioncounsellingapp.utils.FragmentLoadFunction.replaceFragment;

public class BlogFragment extends Fragment implements BlogBinder.BinderListener{

    private MultiViewAdapter adapter;


    private BlogViewModel blogViewModel;


    public BlogFragment() {
        // Required empty public constructor
    }

    public static BlogFragment newInstance() {
        return new BlogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blogViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(BlogViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentBlogBinding binding = FragmentBlogBinding.inflate(inflater, container, false);

        blogViewModel.populateBlogList();

        binding.toolbar.setNavigationOnClickListener(view -> {
            assert getActivity() != null;
            getActivity().onBackPressed();
        });

        setupRecyclerView(binding.rvBlogList);
        setupActionButton(binding.fab);

        return binding.getRoot();
    }

    private void setupActionButton(FloatingActionButton actionButton) {

        actionButton.setOnClickListener(view -> replaceFragment(CreateBlog.newInstance(), "CreateBlog", getFragmentManager()));

    }

    private void setupRecyclerView(RecyclerView recyclerView) {

        adapter = new MultiViewAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        BlogBinder blogBinder = new BlogBinder(this);

        adapter.registerItemBinders(blogBinder);

        ListSection<Blog> blogListSection = new ListSection<>();

        blogViewModel.getBlogList().observe(getViewLifecycleOwner(), blog -> {
            blogListSection.addAll(blog);
            adapter.notifyItemChanged(blogListSection.size() - 1);
        });

        adapter.addSection(blogListSection);
    }

    @Override
    public void onBlogClicked(Blog blog) {

    }
}
