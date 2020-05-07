/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 11:11 AM
 *
 */

package com.example.drugaddictioncounsellingapp.home.binder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.LayoutMentorCardBinding;
import com.example.drugaddictioncounsellingapp.mentor.Mentor;

import mva2.extension.DBItemBinder;

public class MentorBinder extends DBItemBinder<Mentor, LayoutMentorCardBinding> {

    private static final String TAG = "MentorBinder";

    private MentorListener listener;

    public MentorBinder(MentorListener listener) {
        this.listener = listener;
    }

    @Override
    protected void bindModel(Mentor mentor, LayoutMentorCardBinding binding) {
        binding.setMentor(mentor);
        binding.relativeView.setOnClickListener(view -> {
            Log.d(TAG, "bindModel: name@" + mentor.getMentorName());
            listener.onMentorCardClicked(mentor);
        });
    }

    @Override
    protected LayoutMentorCardBinding createBinding(ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_mentor_card, parent, false);
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof Mentor;
    }

    public interface MentorListener {
        void onMentorCardClicked(Mentor mentor);
    }

}
