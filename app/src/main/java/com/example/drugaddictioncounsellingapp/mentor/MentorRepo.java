/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 2:24 PM
 *
 */

package com.example.drugaddictioncounsellingapp.mentor;

import com.example.drugaddictioncounsellingapp.firebase.FirebaseMethods;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class MentorRepo {

    private static MentorRepo instance;
    private FirebaseMethods methods = new FirebaseMethods();

    public static MentorRepo getInstance() {
        if (instance == null) instance = new MentorRepo();
        return instance;
    }

    public Observable<List<Mentor>> getTopMentors() {
        return methods.getTopMentors();
    }

}
