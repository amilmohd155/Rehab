/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 8:19 PM
 *
 */

package com.example.drugaddictioncounsellingapp.startup;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.LayoutFrameBinding;
import com.example.drugaddictioncounsellingapp.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        LayoutFrameBinding binding = LayoutFrameBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

            auth.addAuthStateListener(firebaseAuth -> {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, HomeFragment.newInstance(), "HomeFragment");

                    fragmentTransaction.commitAllowingStateLoss();
                }else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, LoginFragment.newInstance(), "LoginFragment");

                    fragmentTransaction.commitAllowingStateLoss() ;
                }
            });

    }

}
