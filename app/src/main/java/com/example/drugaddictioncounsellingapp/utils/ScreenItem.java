/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/9/20 12:24 AM
 *
 */

package com.example.drugaddictioncounsellingapp.utils;

class ScreenItem {

    private int onBoardingImage;
    private int onBoardingText;

    public ScreenItem(int onBoardingImage, int onBoardingText) {
        this.onBoardingImage = onBoardingImage;
        this.onBoardingText = onBoardingText;
    }

    public int getOnBoardingImage() {
        return onBoardingImage;
    }

    public void setOnBoardingImage(int onBoardingImage) {
        this.onBoardingImage = onBoardingImage;
    }

    public int getOnBoardingText() {
        return onBoardingText;
    }

    public void setOnBoardingText(int onBoardingText) {
        this.onBoardingText = onBoardingText;
    }
}
