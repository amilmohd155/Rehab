/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 9:31 PM
 *
 */

package com.example.drugaddictioncounsellingapp.blog;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drugaddictioncounsellingapp.firebase.FirebaseListener;
import com.example.drugaddictioncounsellingapp.home.model.Blog;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BlogViewModel extends ViewModel {

    private static final String TAG = "BlogViewModel";

    private CompositeDisposable disposables = new CompositeDisposable();
    private Disposable disposable;
    private FirebaseListener firebaseListener;
    private BlogRepository repository;

    private MutableLiveData<List<Blog>> blogList;

    public void populateBlogList() {

        blogList = new MutableLiveData<>();
        repository = BlogRepository.getInstance();

        disposable = repository.getBlog()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(blog -> {
                    blogList.setValue(blog);
//                    firebaseListener.onSuccess();
                    Log.d(TAG, "populateBlogList: success@" + blog.get(0).getName());
                }, failure -> {
                    firebaseListener.onFailure(failure.getMessage());
                    Log.d(TAG, "populateBlogList: " + failure.getMessage());
                });

        disposables.add(disposable);
    }

    public void setFirebaseListener(FirebaseListener firebaseListener) {
        this.firebaseListener = firebaseListener;
    }

    public MutableLiveData<List<Blog>> getBlogList() {
        if (blogList == null) blogList = new MutableLiveData<>();
        return blogList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

}
