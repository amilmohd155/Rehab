/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 12:13 PM
 *
 */

package com.example.drugaddictioncounsellingapp.discussions;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Chat {

    @ServerTimestamp
    private Date createdAt;

    private String senderId;
    private String senderName;
    private String senderPhotoUrl;
    private String text;

    public Chat() {
    }

    public Chat(Date createdAt, String senderId, String senderName, String senderPhotoUrl, String text) {
        this.createdAt = createdAt;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderPhotoUrl = senderPhotoUrl;
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhotoUrl() {
        return senderPhotoUrl;
    }

    public void setSenderPhotoUrl(String senderPhotoUrl) {
        this.senderPhotoUrl = senderPhotoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
