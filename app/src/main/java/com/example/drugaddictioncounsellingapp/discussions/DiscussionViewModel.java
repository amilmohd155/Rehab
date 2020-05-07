/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 12:47 PM
 *
 */

package com.example.drugaddictioncounsellingapp.discussions;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drugaddictioncounsellingapp.discussions.model.Discussion;
import com.example.drugaddictioncounsellingapp.firebase.FirebaseListener;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DiscussionViewModel extends ViewModel {

    private static final String TAG = "DiscussionViewModel";

    private CompositeDisposable disposables = new CompositeDisposable();
    private Disposable disposable;

    private FirebaseListener firebaseListener;

    private DiscussionsRepository repository;

    private MutableLiveData<List<Discussion>> discussionsList;

    public void populateDiscussionsList(long limit) {

        discussionsList = new MutableLiveData<>();
        repository = DiscussionsRepository.getInstance();

        disposable = repository.getDiscussions(limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(discussions -> {
                    discussionsList.setValue(discussions);
                    firebaseListener.onSuccess();
                    Log.d(TAG, "populateBlogList: success@" + discussions.get(0).getDiscussionName());
                }, failure -> {
                    firebaseListener.onFailure(failure.getMessage());
                    Log.d(TAG, "populateBlogList: " + failure.getMessage());
                });

        disposables.add(disposable);
    }

    public MutableLiveData<List<Discussion>> getDiscussionsList() {
        if (discussionsList == null) new MutableLiveData<>();
        return discussionsList;
    }

    public FirebaseListener getFirebaseListener() {
        return firebaseListener;
    }

    public void setFirebaseListener(FirebaseListener firebaseListener) {
        this.firebaseListener = firebaseListener;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
