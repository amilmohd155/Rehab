/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 7:39 PM
 *
 */

package com.example.drugaddictioncounsellingapp.utils;

import androidx.fragment.app.FragmentManager;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.discussions.DiscussionsFragment;
import com.example.drugaddictioncounsellingapp.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.drugaddictioncounsellingapp.utils.FragmentLoadFunction.replaceFragment;

public class BottomNavigationViewHelper {

    public static void enableNavigation(BottomNavigationView navigationView, FragmentManager fragmentManager) {
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(HomeFragment.newInstance(), "HomeFragment", fragmentManager);
                    break;
                case R.id.chat:
                    replaceFragment(DiscussionsFragment.newInstance(), "DiscussionsFragment", fragmentManager);
                    break;
            }
            return true;
        });
    }

}
