/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 7:30 PM
 *
 */

package com.example.drugaddictioncounsellingapp.mentor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drugaddictioncounsellingapp.R;

public class UpdateMentor extends Fragment {

    public UpdateMentor() {
    }

    public static UpdateMentor newInstance() {

        Bundle args = new Bundle();

        UpdateMentor fragment = new UpdateMentor();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_update_mentor, container, false);


    }
}
