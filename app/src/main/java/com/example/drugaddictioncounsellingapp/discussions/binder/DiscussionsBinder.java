/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 7:59 PM
 *
 */

package com.example.drugaddictioncounsellingapp.discussions.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.databinding.LayoutDiscussionListItemBinding;
import com.example.drugaddictioncounsellingapp.discussions.model.Discussion;

import mva2.extension.DBItemBinder;

public class DiscussionsBinder extends DBItemBinder<Discussion, LayoutDiscussionListItemBinding> {

    private BinderListener listener;

    public DiscussionsBinder(BinderListener listener) {
        this.listener = listener;
    }

    @Override
    protected void bindModel(Discussion discussion, LayoutDiscussionListItemBinding binding) {
        binding.setDiscussion(discussion);
        binding.getRoot().setOnClickListener(view -> listener.onDiscussionClicked(discussion.getChatId()));
    }

    @Override
    protected LayoutDiscussionListItemBinding createBinding(ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_discussion_list_item, parent, false);
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof Discussion;
    }

    public interface BinderListener {
        void onDiscussionClicked(String chatId);
    }

}
