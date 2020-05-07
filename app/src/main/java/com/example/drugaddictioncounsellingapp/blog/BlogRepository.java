/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 9:05 PM
 *
 */

package com.example.drugaddictioncounsellingapp.blog;

import com.example.drugaddictioncounsellingapp.firebase.FirebaseMethods;
import com.example.drugaddictioncounsellingapp.home.model.Blog;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

class BlogRepository {

    private static BlogRepository instance;

    private FirebaseMethods methods = new FirebaseMethods();


    public static BlogRepository getInstance() {
        if (instance == null) instance = new BlogRepository();
        return instance;
    }


    public Observable<List<Blog>> getBlog() {
        return methods.getBlog();
    }
}
