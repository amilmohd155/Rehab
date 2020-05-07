/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/10/20 8:51 PM
 *
 */

package com.example.drugaddictioncounsellingapp.startup;

public interface AuthListener {

    void onSuccess();
    void onStarted();
    void onFailure(String message);

}
