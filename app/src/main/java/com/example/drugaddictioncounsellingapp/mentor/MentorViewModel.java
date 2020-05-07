/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 6:52 PM
 *
 */

package com.example.drugaddictioncounsellingapp.mentor;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drugaddictioncounsellingapp.firebase.FirebaseListener;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MentorViewModel extends ViewModel {

    private static final String TAG = "MentorViewModel";

    private CompositeDisposable disposables = new CompositeDisposable();
    private Disposable disposable;
    private FirebaseListener firebaseListener;
    private MentorRepo repo;

    private MutableLiveData<List<Mentor>> mentorsList;

    public void populateTopMentorsList() {

        mentorsList = new MutableLiveData<>();
        repo = MentorRepo.getInstance();

        disposable = repo.getTopMentors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mentors -> {
                    mentorsList.setValue(mentors);
                    firebaseListener.onSuccess();
                }, failure -> {
                    firebaseListener.onFailure(failure.getMessage());
                });

        disposables.add(disposable);

    }

    public MutableLiveData<List<Mentor>> getMentorsList() {

        if (mentorsList == null) mentorsList = new MutableLiveData<>();
        return mentorsList;
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
