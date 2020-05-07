/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 7:27 PM
 *
 */

package com.example.drugaddictioncounsellingapp.discussions;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.LayoutChatReceivedBinding;
import com.example.drugaddictioncounsellingapp.databinding.LayoutChatSendBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChatAdapter extends FirestoreRecyclerAdapter<Chat, ChatAdapter.ChatViewHolder> {


    String userId;

    private final int MESSAGE_IN_VIEW_TYPE  = 1;
    private final int MESSAGE_OUT_VIEW_TYPE = 2;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See
     * {@link FirestoreRecyclerOptions} for configuration options.
     *
     * @param options '
     * @param userId userId of the current user
     */
    public ChatAdapter(FirestoreRecyclerOptions<Chat> options, String userId) {
        super(options);
        this.userId = userId;
    }

    @Override
    public int getItemViewType(int position) {
        //if message userId matches current userId, set view type 1 else set view type 2
        if(getItem(position).getSenderId().equals(userId)){
            return MESSAGE_OUT_VIEW_TYPE;
        }
        return MESSAGE_IN_VIEW_TYPE;
    }

    @Override
    protected void onBindViewHolder(ChatViewHolder holder, int i, Chat chat) {

        switch (holder.getItemViewType()) {
            case MESSAGE_IN_VIEW_TYPE:
                LayoutChatReceivedBinding receivedBinding = holder.chatReceivedBinding;
                receivedBinding.setChat(chat);
                break;
            case MESSAGE_OUT_VIEW_TYPE:
                LayoutChatSendBinding sendBinding = holder.chatSendBinding;
                sendBinding.setChat(chat);
                break;
        }

    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding;

        switch (viewType) {
            case MESSAGE_IN_VIEW_TYPE:
                binding = DataBindingUtil.inflate(inflater, R.layout.layout_chat_received, parent, false);
                return new ChatViewHolder((LayoutChatReceivedBinding) binding);
            case MESSAGE_OUT_VIEW_TYPE:
                binding = DataBindingUtil.inflate(inflater, R.layout.layout_chat_send, parent, false);
                return new ChatViewHolder((LayoutChatSendBinding) binding);
        }

        return null;

    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {

        private LayoutChatReceivedBinding chatReceivedBinding;
        private LayoutChatSendBinding chatSendBinding;

        public ChatViewHolder(LayoutChatReceivedBinding chatReceivedBinding) {
            super(chatReceivedBinding.getRoot());
            this.chatReceivedBinding = chatReceivedBinding;
        }

        public ChatViewHolder(LayoutChatSendBinding chatSendBinding) {
            super(chatSendBinding.getRoot());
            this.chatSendBinding = chatSendBinding;
        }
    }
}
