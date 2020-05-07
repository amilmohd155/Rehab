/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 8:07 PM
 *
 */

package com.example.drugaddictioncounsellingapp.mentor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.FragmentMentorBinding;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class MentorFragment extends Fragment {

    private static final String MENTOR_KEY = "Mentor";
    public static final String END_TIME = "endTime";
    public static final String START_TIME = "startTime";
    public static final String WEEK_DAY = "weekDay";

    private FragmentMentorBinding binding;
    private Mentor mentor;

    private RecyclerView recyclerView;
    private MultiViewAdapter adapter;

    public static MentorFragment newInstance(Mentor mentor) {

        Bundle args = new Bundle();
        args.putParcelable(MENTOR_KEY, mentor);
        MentorFragment fragment = new MentorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mentor = getArguments().getParcelable(MENTOR_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor, container, false);
        binding.setMentor(mentor);
        binding.setFragment(this);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
    }
}
