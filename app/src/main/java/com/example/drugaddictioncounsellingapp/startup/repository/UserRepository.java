/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 8:23 AM
 *
 */

package com.example.drugaddictioncounsellingapp.startup.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.drugaddictioncounsellingapp.firebase.FirebaseMethods;
import com.example.drugaddictioncounsellingapp.startup.model.User;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class UserRepository {

    private static UserRepository instance;
    private FirebaseMethods methods = new FirebaseMethods();

    public static UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }

    public Completable register(String email, String password) {
        return methods.register(email, password);
    }

    public void addUserToFireStore(User user) {
        methods.addUserToFireStore(user);
    }

    public Completable login(String email, String password) {
        return methods.login(email, password);
    }

    public Observable<User> getCurrentUser() {
        return methods.getCurrentUser();
    }
}
