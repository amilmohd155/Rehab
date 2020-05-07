/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/18/20 9:53 PM
 *
 */

package com.example.drugaddictioncounsellingapp.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Blog implements Parcelable {

    @ServerTimestamp
    private Date timeStamp;

    private String blogID;
    private String photoUrl;
    private String name;
    private String coverUrl;
    private String heading;
    private String blog;

    public Blog() {
    }

    public Blog(Date timeStamp, String blogID, String photoUrl, String name, String coverUrl, String heading) {
        this.timeStamp = timeStamp;
        this.blogID = blogID;
        this.photoUrl = photoUrl;
        this.name = name;
        this.coverUrl = coverUrl;
        this.heading = heading;
    }

    public Blog(Date timeStamp, String blogID, String photoUrl, String name, String coverUrl, String heading, String blog) {
        this.timeStamp = timeStamp;
        this.blogID = blogID;
        this.photoUrl = photoUrl;
        this.name = name;
        this.coverUrl = coverUrl;
        this.heading = heading;
        this.blog = blog;
    }

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    @PropertyName("timestamp")
    public Date getTimeStamp() {
        return timeStamp;
    }

    @PropertyName("timestamp")
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBlog() {
        if (blog == null) blog = "";
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }


    //Parcelling

    public Blog(Parcel in) {
        this.timeStamp = new Date(in.readLong());
        this.blogID = in.readString();
        this.photoUrl = in.readString();
        this.name = in.readString();
        this.coverUrl = in.readString();
        this.heading = in.readString();
        this.blog = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Blog createFromParcel(Parcel in) {
            return new Blog(in);
        }

        public Blog[] newArray(int size) {
            return new Blog[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.timeStamp.getTime());
        parcel.writeString(this.blogID);
        parcel.writeString(this.photoUrl);
        parcel.writeString(this.name);
        parcel.writeString(this.coverUrl);
        parcel.writeString(this.heading);
        parcel.writeString(this.blog);
    }

}
