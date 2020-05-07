/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 11:55 AM
 *
 */

package com.example.drugaddictioncounsellingapp.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.FragmentEditProfileBinding;
import com.example.drugaddictioncounsellingapp.startup.model.User;
import com.example.drugaddictioncounsellingapp.startup.viewModel.UserViewModel;
import com.example.drugaddictioncounsellingapp.utils.GlideApp;
import com.google.android.material.button.MaterialButton;

import static android.app.Activity.RESULT_OK;

public class EditProfileFragment extends Fragment {

    private static final int REQUEST_CODE = 1;

    private FragmentEditProfileBinding binding;

    private UserViewModel userViewModel;
    private User newUser, oldUser;

    private MaterialButton button;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false);

        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setUserViewModel(userViewModel);
        binding.setFragment(this);

        userViewModel.getCurrentUserData();

        oldUser = userViewModel.getUser().getValue();

        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            newUser = user;
        });

        button = binding.materialButton;

        binding.profilePhoto.setOnClickListener(view -> changeProfilePicture());

        return binding.getRoot();
    }

    private void changeProfilePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                Uri selectedImage = data.getData();

                GlideApp.with(this)
                        .load(selectedImage)
                        .into(binding.profilePhoto);

                userViewModel.changeUserProfilePicture(selectedImage, newUser.getPhotoUrl());

            }
        }
    }
}
