/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 5:29 PM
 *
 */

package com.example.drugaddictioncounsellingapp.mentor;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mentor implements Parcelable {

    private String mentorPhotoUrl;
    private String mentorName;
    private String designation;
    private String background;
    private String clinic;
    private List<String> speciality;
    private Map<String, String> timing;

    private int heat;

    public Mentor() {
    }

    public Mentor(String mentorPhotoUrl, String mentorName, String designation) {
        this.mentorPhotoUrl = mentorPhotoUrl;
        this.mentorName = mentorName;
        this.designation = designation;
    }

    public Mentor(String mentorPhotoUrl, String mentorName, String designation, String background, String clinic, List<String> speciality, Map<String, String> timing, int heat) {
        this.mentorPhotoUrl = mentorPhotoUrl;
        this.mentorName = mentorName;
        this.designation = designation;
        this.background = background;
        this.clinic = clinic;
        this.speciality = speciality;
        this.timing = timing;
        this.heat = heat;
    }

    public String getMentorPhotoUrl() {
        return mentorPhotoUrl;
    }

    public void setMentorPhotoUrl(String mentorPhotoUrl) {
        this.mentorPhotoUrl = mentorPhotoUrl;
    }

    public String getMentorName() {
        if (mentorName == null) mentorName = "";
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getDesignation() {
        if (designation == null) designation = "";
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBackground() {
        if (background == null) background = "";
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getClinic() {
        if (clinic == null) clinic = "";
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public List<String> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(List<String> speciality) {
        this.speciality = speciality;
    }

    public Map<String, String> getTiming() {
        return timing;
    }

    public void setTiming(Map<String, String> timing) {
        this.timing = timing;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public Mentor(Parcel in) {
        this.mentorPhotoUrl = in.readString();
        this.mentorName = in.readString();
        this.designation = in.readString();
        this.background = in.readString();
        this.clinic = in.readString();
        in.readList(this.speciality, String.class.getClassLoader());
        in.readMap(timing, getClass().getClassLoader());
        this.heat = in.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Mentor createFromParcel(Parcel in) {
            return new Mentor(in);
        }

        public Mentor[] newArray(int size) {
            return new Mentor[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mentorPhotoUrl);
        parcel.writeString(this.mentorName);
        parcel.writeString(this.designation);
        parcel.writeString(this.background);
        parcel.writeString(this.clinic);
        parcel.writeList(this.speciality);
        parcel.writeMap(this.timing);
        parcel.writeInt(this.heat);
    }
}
