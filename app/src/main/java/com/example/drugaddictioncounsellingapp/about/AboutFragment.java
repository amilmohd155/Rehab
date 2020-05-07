/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/14/20 12:37 PM
 *
 */

package com.example.drugaddictioncounsellingapp.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
