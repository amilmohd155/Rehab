/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 7:24 PM
 *
 */

package com.example.drugaddictioncounsellingapp.discussions;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugaddictioncounsellingapp.databinding.FragmentChatRoomBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.example.drugaddictioncounsellingapp.firebase.FirebaseMethods.CHAT_ROOMS_COLLECTION;
import static com.example.drugaddictioncounsellingapp.firebase.FirebaseMethods.MESSAGES_COLLECTION;

public class ChatRoomFragment extends Fragment {

    private static final String TAG = "ChatRoomFragment";

    private static final String CHAT_ID = "chatID";

    private String chatId;
    private FirebaseAuth auth;
    private FirebaseUser user;

    private ChatAdapter adapter;

    private EditText etMessage;
    private ImageButton sendBtn;


    public ChatRoomFragment() {
    }

    public static ChatRoomFragment newInstance(String chatId) {

        Bundle args = new Bundle();
        args.putString(CHAT_ID, chatId);
        ChatRoomFragment fragment = new ChatRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            chatId = getArguments().getString(CHAT_ID);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentChatRoomBinding binding = FragmentChatRoomBinding.inflate(inflater, container, false);

        etMessage = binding.etMessage;
        sendBtn = binding.ibSubmit;

        Query query = FirebaseFirestore.getInstance()
                .collection(CHAT_ROOMS_COLLECTION)
                .document(chatId)
                .collection(MESSAGES_COLLECTION)
                .orderBy("createdAt", Query.Direction.ASCENDING)
                .limitToLast(15);

        FirestoreRecyclerOptions<Chat> options = new FirestoreRecyclerOptions.Builder<Chat>()
                .setQuery(query, snapshot -> snapshot.toObject(Chat.class))
                .setLifecycleOwner(getViewLifecycleOwner())
                .build();

        adapter = new ChatAdapter(options, user.getUid());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                binding.recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }
        });

        binding.toolbar.setNavigationOnClickListener(view -> {
            if (getActivity() != null)
                getActivity().onBackPressed();
        });

        setUpMessage();

        return binding.getRoot();

    }

    public void setUpMessage() {

        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) sendBtn.setEnabled(true);
                else sendBtn.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sendBtn.setOnClickListener(view -> {
            onAddChat(new Chat(
                    null,
                    user.getUid(),
                    user.getDisplayName(),
                    user.getPhotoUrl().toString(),
                    etMessage.getText().toString().trim()
            ));
            etMessage.getText().clear();
        });


    }

    private void onAddChat(Chat chat) {
        FirebaseFirestore.getInstance()
                .collection(CHAT_ROOMS_COLLECTION)
                .document(chatId)
                .collection(MESSAGES_COLLECTION)
                .add(chat)
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful())
                        Log.d(TAG, "onAddChat: error:" + task.getException());
                });
    }

}
