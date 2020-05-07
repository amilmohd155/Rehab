/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 7:59 PM
 *
 */

package com.example.drugaddictioncounsellingapp.discussions.model;

import java.util.ArrayList;
import java.util.Date;

public class Discussion {

    private String chatId;

    private String photoUrl;
    private String discussionName;
    private String lastMessage;

    private Date lastMessageTimeStamp;

    private ArrayList<String> participants;

    public Discussion() {
    }

    public Discussion(String chatId, String photoUrl, String discussionName, String lastMessage, Date lastMessageTimeStamp, ArrayList<String> participants) {
        this.chatId = chatId;
        this.photoUrl = photoUrl;
        this.discussionName = discussionName;
        this.lastMessage = lastMessage;
        this.lastMessageTimeStamp = lastMessageTimeStamp;
        this.participants = participants;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDiscussionName() {
        return discussionName;
    }

    public void setDiscussionName(String discussionName) {
        this.discussionName = discussionName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Date getLastMessageTimeStamp() {
        return lastMessageTimeStamp;
    }

    public void setLastMessageTimeStamp(Date lastMessageTimeStamp) {
        this.lastMessageTimeStamp = lastMessageTimeStamp;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }
}
