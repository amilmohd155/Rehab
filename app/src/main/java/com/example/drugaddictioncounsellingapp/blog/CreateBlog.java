/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/8/20 11:37 PM
 *
 */

package com.example.drugaddictioncounsellingapp.blog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drugaddictioncounsellingapp.R;

public class CreateBlog extends Fragment {

    public CreateBlog() {
        // Required empty public constructor
    }

    public static CreateBlog newInstance() {
        return new CreateBlog();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_blog, container, false);
    }
}
