/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 7:56 PM
 *
 */

package com.example.drugaddictioncounsellingapp.discussions;

import com.example.drugaddictioncounsellingapp.discussions.model.Discussion;
import com.example.drugaddictioncounsellingapp.firebase.FirebaseMethods;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class DiscussionsRepository {

    private static DiscussionsRepository repository;

    private FirebaseMethods methods;

    public static DiscussionsRepository getInstance() {
        if (repository == null) repository = new DiscussionsRepository();
        return repository;
    }

    public Observable<List<Discussion>> getDiscussions(long limit) {
        methods = new FirebaseMethods();

        return methods.getDiscussions(limit);

    }

}
