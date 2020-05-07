/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 8:07 AM
 *
 */

package com.example.drugaddictioncounsellingapp.firebase;

public interface FirebaseListener {

    void onSuccess();
    void onStarted();
    void onFailure(String message);

}
