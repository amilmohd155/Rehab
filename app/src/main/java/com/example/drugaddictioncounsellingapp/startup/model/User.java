/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/17/20 1:47 PM
 *
 */

package com.example.drugaddictioncounsellingapp.startup.model;

import android.util.Patterns;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class User {

    @ServerTimestamp
    private Date timeStamp;

    private String displayName;
    private String email;
    private String photoUrl;

    private int streak;

    private String mentorName;
    private String mentorId;

    private boolean isMentorAvailable;

    public User() {
    }

    public User(Date timeStamp, String displayName, String email, String photoUrl) {
        this.timeStamp = timeStamp;
        this.displayName = displayName;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public User(Date timeStamp, String displayName, String email, String photoUrl, int streak, String mentorName, String mentorId, boolean isMentorAvailable) {
        this.timeStamp = timeStamp;
        this.displayName = displayName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.streak = streak;
        this.mentorName = mentorName;
        this.mentorId = mentorId;
        this.isMentorAvailable = isMentorAvailable;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    @PropertyName("mentor")
    public String getMentorName() {
        return mentorName;
    }

    @PropertyName("mentor")
    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    @PropertyName("isMentorAvailable")
    public boolean isMentorAvailable() {
        return isMentorAvailable;
    }

    @PropertyName("isMentorAvailable")
    public void setMentorAvailable(boolean mentorAvailable) {
        isMentorAvailable = mentorAvailable;
    }

    @Exclude
    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

//    private Date timeStamp;
//
//    private String displayName;
//    private String email;
//    private String photoUrl;
//
//    private int streak;
//
//    private String mentorName;
//    private String mentorId;
//
//    private boolean isMentorAvailable;

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof User &&
                this.displayName.equals(((User)obj).displayName) &&
                this.email.equals(((User)obj).email) &&
                this.mentorName.equals(((User) obj).mentorName);
    }
}
