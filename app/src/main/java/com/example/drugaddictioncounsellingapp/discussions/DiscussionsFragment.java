/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 12:47 PM
 *
 */

package com.example.drugaddictioncounsellingapp.discussions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.FragmentDiscussionsBinding;
import com.example.drugaddictioncounsellingapp.discussions.binder.DiscussionsBinder;
import com.example.drugaddictioncounsellingapp.discussions.model.Discussion;
import com.example.drugaddictioncounsellingapp.firebase.FirebaseListener;
import com.example.drugaddictioncounsellingapp.utils.BottomNavigationViewHelper;
import com.facebook.shimmer.ShimmerFrameLayout;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

import static com.example.drugaddictioncounsellingapp.utils.FragmentLoadFunction.replaceFragment;

public class DiscussionsFragment extends Fragment implements FirebaseListener, DiscussionsBinder.BinderListener {

    private static long LIMIT = 15L;

    private FragmentDiscussionsBinding binding;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ShimmerFrameLayout shimmerFrameLayout;

    private MultiViewAdapter adapter;
    private DiscussionViewModel discussionViewModel;

    public DiscussionsFragment() {
    }

    public static DiscussionsFragment newInstance() {

        Bundle args = new Bundle();

        DiscussionsFragment fragment = new DiscussionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        discussionViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DiscussionViewModel.class);

        discussionViewModel.setFirebaseListener(this);
        discussionViewModel.populateDiscussionsList(LIMIT);

        binding = FragmentDiscussionsBinding.inflate(inflater, container, false);

        recyclerView = binding.recyclerView;
        shimmerFrameLayout = binding.shimmer;

        setupRecyclerView();

        binding.bottomNav.setSelectedItemId(R.id.chat);
        BottomNavigationViewHelper.enableNavigation(binding.bottomNav, getFragmentManager());

        return binding.getRoot();
    }


    private void setupRecyclerView() {

        adapter = new MultiViewAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DiscussionsBinder binder = new DiscussionsBinder(this);

        adapter.registerItemBinders(binder);

        ListSection<Discussion> discussionsSection = new ListSection<>();

        discussionViewModel.getDiscussionsList().observe(getViewLifecycleOwner(), discussions -> {
            discussionsSection.addAll(discussions);
            adapter.notifyItemChanged(discussionsSection.size() -1);
        });

        adapter.addSection(discussionsSection);

    }

    @Override
    public void onSuccess() {
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
    public void onDiscussionClicked(String chatId) {
        replaceFragment(ChatRoomFragment.newInstance(chatId), "ChatRoomFragment", getFragmentManager());
    }
}
