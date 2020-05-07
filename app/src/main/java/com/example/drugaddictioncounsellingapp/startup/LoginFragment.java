/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 8:33 AM
 *
 */

package com.example.drugaddictioncounsellingapp.startup;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.FragmentLoginBinding;
import com.example.drugaddictioncounsellingapp.home.HomeFragment;
import com.example.drugaddictioncounsellingapp.startup.viewModel.UserViewModel;
import com.example.drugaddictioncounsellingapp.utils.ProgressDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.drugaddictioncounsellingapp.utils.FragmentLoadFunction.replaceFragment;
import static com.example.drugaddictioncounsellingapp.utils.ProgressDialog.LIGHT_THEME;

public class LoginFragment extends Fragment implements AuthListener{

    private static final String TAG = "LoginFragment";

    private FragmentLoginBinding binding;
    private UserViewModel userViewModel;
    private ProgressDialog.Builder builder;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setLifecycleOwner(this);

        binding.setFragment(this);
        binding.setUserViewModel(userViewModel);

        userViewModel.setAuthListener(this);

        return binding.getRoot();
    }

    public void onSignUpLinkClicked() {
        replaceFragment(SignUpFragment.newInstance(), "SignUpFragment", getFragmentManager());
    }

    @Override
    public void onSuccess() {

        Log.d(TAG, "onSuccess: email@" + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        builder.dismiss();
        //move to home fragment
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        replaceFragment(HomeFragment.newInstance(), "HomeFragment", getFragmentManager());
    }

    @Override
    public void onStarted() {
        builder.build();
    }

    @Override
    public void onFailure(String message) {
        builder.dismiss();
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}
