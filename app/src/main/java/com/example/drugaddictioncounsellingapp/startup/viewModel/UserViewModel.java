/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 4:48 PM
 *
 */

package com.example.drugaddictioncounsellingapp.startup.viewModel;

import android.net.Uri;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drugaddictioncounsellingapp.firebase.FirebaseListener;
import com.example.drugaddictioncounsellingapp.firebase.FirebaseMethods;
import com.example.drugaddictioncounsellingapp.startup.AuthListener;
import com.example.drugaddictioncounsellingapp.startup.model.User;
import com.example.drugaddictioncounsellingapp.startup.repository.UserRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class UserViewModel extends ViewModel {

    private static final String TAG = "UserViewModel";

    private CompositeDisposable disposables = new CompositeDisposable();
    private Disposable disposable;
    private AuthListener authListener;
    private FirebaseListener firebaseListener;
    private UserRepository repository;

    public MutableLiveData<String> errorName = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();

    public MutableLiveData<Integer> progressValue = new MutableLiveData<>();

    private MutableLiveData<User> user;
    private MutableLiveData<String> email;
    private MutableLiveData<String> name;
    private MutableLiveData<String> password;

    public MutableLiveData<String> getEmail() {
        if (email == null) email = new MutableLiveData<>();
        return email;
    }

    public MutableLiveData<String> getName() {
        if (name == null) name = new MutableLiveData<>();
        return name;
    }

    public MutableLiveData<String> getPassword() {
        if (password == null) password = new MutableLiveData<>();
        return password;
    }

    public void onSignUpClicked() {

        repository = UserRepository.getInstance();

        boolean flag = true;

        User user = new User(
                null,
                name.getValue(),
                email.getValue(),
                null
        );

        if (user.getEmail().isEmpty() || !user.isEmailValid()) {
            flag = false;
            errorEmail.setValue("Enter a valid email address");
        }
        else errorEmail.setValue(null);

        if (user.getDisplayName().isEmpty()) {
            flag = false;
            errorName.setValue("Enter a full name");
        }else errorName.setValue(null);

        if (password.getValue() == null || password.getValue().length() < 5) {
            flag = false;
            errorPassword.setValue("Enter at least a 5 digit password");
        } else errorPassword.setValue(null);

        if (flag) {

            authListener.onStarted();

            //to firebase
            disposable = repository.register(email.getValue(), password.getValue())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(()-> {
                                repository.addUserToFireStore(user);
                                authListener.onSuccess(); },
                            (f) -> authListener.onFailure(f.getMessage())
                    );
            disposables.add(disposable);

        }

    }

    public void onLoginCLicked() {

        repository = UserRepository.getInstance();

        boolean flag = true;

        if (email.getValue() == null || !isEmail(email.getValue())) {
            flag = false;
            errorEmail.setValue("Enter valid email address");
        }else errorEmail.setValue(null);

        if (password.getValue() == null || !isPasswordGreaterThanFive(password.getValue())) {
            flag = false;
            errorPassword.setValue("Enter at least a 5 digit password");
        } else errorPassword.setValue(null);

         if (flag) {
             authListener.onStarted();

             disposable = repository.login(email.getValue(), password.getValue())
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(()-> {
                         authListener.onSuccess();
                     }, (f) -> authListener.onFailure(f.getMessage()));

             disposables.add(disposable);
         }
    }

    public boolean checkSignUpInput(String email, String name, String password){
        return email != null && name != null && password != null;
    }

    public void getCurrentUserData() {

        progressValue.setValue(View.VISIBLE);
        user = new MutableLiveData<>();

        repository = UserRepository.getInstance();
        disposable = repository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((user) -> {
                    this.user.setValue(user);
                    progressValue.setValue(View.GONE);
                }, (f) -> {
                    Log.d(TAG, "getCurrentUserData: error@" + f.getMessage());
                });

        disposables.add(disposable);

    }

    public void changeUserProfilePicture(Uri selectedImage, String photoUrl) {
        new FirebaseMethods().changeUserProfilePicture(selectedImage, photoUrl);
    }

    public LiveData<User> getUser() {
        return user;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    public boolean checkLoginInput(String email, String password) {
        return email != null && password != null;
    }

    private boolean isPasswordGreaterThanFive(String password) {
        return password.length() > 5;
    }

    private boolean isEmail(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

    public void setAuthListener(AuthListener authListener) {
        this.authListener = authListener;
    }

    public void setFirebaseListener(FirebaseListener firebaseListener) {
        this.firebaseListener = firebaseListener;
    }
}
