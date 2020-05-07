/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/10/20 8:14 PM
 *
 */

package com.example.drugaddictioncounsellingapp.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.drugaddictioncounsellingapp.R;

public class FragmentLoadFunction {


    public static void replaceFragment(Fragment fragment, String fragmentName, FragmentManager fragmentManager) {

        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container,fragment , fragmentName)
                    .addToBackStack(fragmentName)
                    .commit();
        }else {
            throw new RuntimeException("FragmentManager is null");
        }

    }


}
