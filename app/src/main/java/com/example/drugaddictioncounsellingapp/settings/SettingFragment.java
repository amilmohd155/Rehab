/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/18/20 1:15 PM
 *
 */

package com.example.drugaddictioncounsellingapp.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.drugaddictioncounsellingapp.R;

public class SettingFragment extends PreferenceFragmentCompat {

    public SettingFragment() {
    }

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting, rootKey);
    }

}
