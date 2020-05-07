/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/18/20 8:36 AM
 *
 */

package com.example.drugaddictioncounsellingapp.streak;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drugaddictioncounsellingapp.R;

public class StreakFragment extends Fragment {

    public StreakFragment() {
    }

    public static StreakFragment newInstance() {

        Bundle args = new Bundle();

        StreakFragment fragment = new StreakFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_streak, container, false);
    }
}
