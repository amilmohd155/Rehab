/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 8:33 AM
 *
 */

package com.example.drugaddictioncounsellingapp.startup;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.FragmentSignUpBinding;
import com.example.drugaddictioncounsellingapp.startup.viewModel.UserViewModel;
import com.example.drugaddictioncounsellingapp.utils.ProgressDialog;
import com.google.android.material.snackbar.Snackbar;

import static com.example.drugaddictioncounsellingapp.utils.FragmentLoadFunction.replaceFragment;
import static com.example.drugaddictioncounsellingapp.utils.ProgressDialog.LIGHT_THEME;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements AuthListener {

    private static final String TAG = "SignUpFragment";

    private FragmentSignUpBinding binding;
    private UserViewModel userViewModel;
    private ProgressDialog.Builder builder;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        builder = new ProgressDialog.Builder(getContext())
                .setMessageVisibility(false)
                .setTheme(LIGHT_THEME);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        binding.setLifecycleOwner(this);
        binding.setUserViewModel(userViewModel);
        binding.setFragment(this);

        userViewModel.setAuthListener(this);

        return binding.getRoot();
    }

    public void onLoginLinkClicked() {
        Log.d(TAG, "onLoginLinkClicked: called");
        replaceFragment(LoginFragment.newInstance(), "LoginFragment", getFragmentManager());
    }

    @Override
    public void onSuccess() {
        builder.dismiss();
        Log.d(TAG, "onSuccess: successfully done");
        //go to home fragment

    }

    @Override
    public void onStarted() {
        builder.build();
    }

    @Override
    public void onFailure(String message) {
        builder.dismiss();
        Log.d(TAG, "onFailure: " + message);
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();

    }
}
