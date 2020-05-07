/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 11:21 AM
 *
 */

package com.example.drugaddictioncounsellingapp.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.about.AboutFragment;
import com.example.drugaddictioncounsellingapp.blog.BlogViewModel;
import com.example.drugaddictioncounsellingapp.blog.fragment.BlogFragment;
import com.example.drugaddictioncounsellingapp.blog.fragment.BlogViewerFragment;
import com.example.drugaddictioncounsellingapp.databinding.FragmentHomeBinding;
import com.example.drugaddictioncounsellingapp.databinding.LayoutNavigationHeaderBinding;
import com.example.drugaddictioncounsellingapp.firebase.FirebaseListener;
import com.example.drugaddictioncounsellingapp.home.binder.BlogBinder;
import com.example.drugaddictioncounsellingapp.home.binder.HeaderItemBinder;
import com.example.drugaddictioncounsellingapp.home.binder.HorizontalScrollBinder;
import com.example.drugaddictioncounsellingapp.home.binder.MentorBinder;
import com.example.drugaddictioncounsellingapp.home.model.Blog;
import com.example.drugaddictioncounsellingapp.mentor.Mentor;
import com.example.drugaddictioncounsellingapp.mentor.MentorFragment;
import com.example.drugaddictioncounsellingapp.mentor.MentorViewModel;
import com.example.drugaddictioncounsellingapp.mentor.UpdateMentor;
import com.example.drugaddictioncounsellingapp.profile.EditProfileFragment;
import com.example.drugaddictioncounsellingapp.settings.SettingFragment;
import com.example.drugaddictioncounsellingapp.startup.viewModel.UserViewModel;
import com.example.drugaddictioncounsellingapp.streak.StreakFragment;
import com.example.drugaddictioncounsellingapp.utils.BottomNavigationViewHelper;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import mva2.adapter.ItemSection;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.NestedSection;

import static com.example.drugaddictioncounsellingapp.utils.FragmentLoadFunction.replaceFragment;

public class HomeFragment extends Fragment implements
        NavigationView.OnNavigationItemSelectedListener,
        FirebaseListener,
        BlogBinder.BinderListener,
        MentorBinder.MentorListener {

    private static final String TAG = "HomeFragment";

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private UserViewModel userViewModel;
    private MentorViewModel mentorViewModel;
    private BlogViewModel blogViewModel;

    private ShimmerFrameLayout shimmerFrameLayout;

    private HorizontalScrollBinder horizontalScrollBinder;
    private BlogBinder blogBinder;
    private MultiViewAdapter adapter;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        mentorViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MentorViewModel.class);
        blogViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(BlogViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        binding.setUserViewModel(userViewModel);
        binding.setFragment(this);

        binding.navigationView.setNavigationItemSelectedListener(this);

        LayoutNavigationHeaderBinding headerBinding = DataBindingUtil.inflate(inflater, R.layout.layout_navigation_header, binding.navigationView, true);
        headerBinding.setLifecycleOwner(this);
        headerBinding.setUserViewModel(userViewModel);

        userViewModel.setFirebaseListener(this);
        userViewModel.getCurrentUserData();

        blogViewModel.setFirebaseListener(this);
        blogViewModel.populateBlogList();

        mentorViewModel.setFirebaseListener(this);
        mentorViewModel.populateTopMentorsList();

        shimmerFrameLayout = binding.shimmer;

        setRecyclerView();

        binding.bottomNav.setSelectedItemId(R.id.home);
        BottomNavigationViewHelper.enableNavigation(binding.bottomNav, getFragmentManager());

        return binding.getRoot();
    }

    private void setRecyclerView() {

        adapter = new MultiViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        horizontalScrollBinder = new HorizontalScrollBinder(this);
        HeaderItemBinder headerItemBinder = new HeaderItemBinder();
        blogBinder = new BlogBinder(this);

        mentorViewModel.getMentorsList().observe(getViewLifecycleOwner(), mentors -> {
            horizontalScrollBinder.setMentors((ArrayList<Mentor>) mentors);
        });


        adapter.registerItemBinders(headerItemBinder, blogBinder, horizontalScrollBinder);

        ItemSection<Integer> mentorHeaderSection = new ItemSection<>(R.string.top_mentors);
        ItemSection<Boolean> mentorHorizontalSection = new ItemSection<>(true);

        NestedSection mentorSection = new NestedSection();
        mentorSection.addSection(mentorHeaderSection);
        mentorSection.addSection(mentorHorizontalSection);

        ItemSection<Integer> blogHeaderSection = new ItemSection<>(R.string.blog_heading);
        ListSection<Blog> blogListSection = new ListSection<>();

        blogViewModel.getBlogList().observe(getViewLifecycleOwner(), blog -> {
            blogListSection.addAll(blog);
            adapter.notifyItemChanged(blogListSection.size() - 1);
        });

        NestedSection blogSection = new NestedSection();
        blogSection.addSection(blogHeaderSection);
        blogSection.addSection(blogListSection);

        adapter.addSection(mentorSection);
        adapter.addSection(blogSection);


        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                replaceFragment(EditProfileFragment.newInstance(), "EditProfile", getFragmentManager());
                break;
            case R.id.change:
                replaceFragment(UpdateMentor.newInstance(), "ChangeMentor", getFragmentManager());
                break;
            case R.id.streak_view:
                replaceFragment(StreakFragment.newInstance(), "StreakFragment", getFragmentManager());
                break;
            case R.id.blog:
                replaceFragment(BlogFragment.newInstance(), "BlogFragment", getFragmentManager());
                break;
            case R.id.setting:
                replaceFragment(SettingFragment.newInstance(), "SettingFragment", getFragmentManager());
                break;
            case R.id.about:
                replaceFragment(AboutFragment.newInstance(), "AboutFragment", getFragmentManager());
        }
        return true;
    }

    @Override
    public void onSuccess() {
        Log.d(TAG, "onSuccess: ");
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStarted() {
        recyclerView.setVisibility(View.GONE);
        shimmerFrameLayout.startShimmer();
    }

    @Override
    public void onFailure(String message) {
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroy: destroyed");
        adapter = null;
        blogBinder = null;
        horizontalScrollBinder.setAdapter(null);
        horizontalScrollBinder = null;
    }

    @Override
    public void onBlogClicked(Blog blog) {
        Log.d(TAG, "setRecyclerView: id@" + blog.getBlogID());
        replaceFragment(BlogViewerFragment.newInstance(blog), "BlogViewerFragment", getFragmentManager());
    }

    @Override
    public void onMentorCardClicked(Mentor mentor) {
        Log.d(TAG, "onMentorCardClicked: clicked, name@" + mentor.getMentorName());
        replaceFragment(MentorFragment.newInstance(mentor), "MentorFragment", getFragmentManager());
    }

}
