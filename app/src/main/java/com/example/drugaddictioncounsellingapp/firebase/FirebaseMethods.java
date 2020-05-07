/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 7:59 PM
 *
 */

package com.example.drugaddictioncounsellingapp.firebase;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.drugaddictioncounsellingapp.discussions.model.Discussion;
import com.example.drugaddictioncounsellingapp.home.model.Blog;
import com.example.drugaddictioncounsellingapp.mentor.Mentor;
import com.example.drugaddictioncounsellingapp.startup.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class FirebaseMethods {

    private static final String TAG = "FirebaseMethods";

    private static final String USER_COLLECTION = "users";
    private static final String MENTOR_COLLECTION = "mentors";
    private static final String BLOG_COLLECTION = "blogs";
    public static final String CHAT_ROOMS_COLLECTION = "chatRooms";
    public static final String MESSAGES_COLLECTION = "messages";

    private static final String HEAT_FIELD = "heat";
    private static final String LAST_MESSAGE_FIELD = "lastMessage";

    private FirebaseAuth auth;
    private FirebaseFirestore rootRef;
    private FirebaseUser currentUser;
    private FirebaseStorage storage;
    private UserProfileChangeRequest profileChangeRequest;

    private User user;

    public FirebaseMethods() {

        auth = FirebaseAuth.getInstance();
        rootRef = FirebaseFirestore.getInstance();
        currentUser = auth.getCurrentUser();
        storage = FirebaseStorage.getInstance();

    }

    public Completable register(String email, String password) {

        return Completable.create(emitter ->
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (!emitter.isDisposed())
                                if (task.isSuccessful()) {
                                    emitter.onComplete();
                                    currentUser = auth.getCurrentUser();
                                } else
                                    emitter.onError(task.getException());
                }));

    }

    public Completable login(String email, String password) {

        return Completable.create(emitter ->
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (!emitter.isDisposed())
                        if (task.isSuccessful()) {
                            currentUser = auth.getCurrentUser();
                            emitter.onComplete();
                        }
                        else
                            emitter.onError(task.getException());
        }));

    }

    public void addUserToFireStore(@NonNull User user) {

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(user.getDisplayName())
                .build();

        if (currentUser != null)
            currentUser.updateProfile(profileUpdates);


        rootRef.collection("users").document(currentUser.getUid()).set(user);

    }

    public Observable<User> getCurrentUser() {

        return Observable.create(emitter -> {
            rootRef.collection(USER_COLLECTION).document(auth.getCurrentUser().getUid())
                    .addSnapshotListener((documentSnapshot, e) -> {
                        if (e != null) {
                            emitter.onError(e);
                        }else
                            user = documentSnapshot.toObject(User.class);
                        emitter.onNext(user);
                    });
        });

    }

    public Observable<List<Mentor>> getTopMentors() {

        return Observable.create(emitter ->
                rootRef.collection(MENTOR_COLLECTION)
                .orderBy(HEAT_FIELD, Query.Direction.ASCENDING)
                .limit(4)
                .get()
                .addOnCompleteListener(task -> {
                    if (!emitter.isDisposed())
                        if (task.isSuccessful())
                            emitter.onNext(task.getResult().toObjects(Mentor.class));
                        else emitter.onError(task.getException());
                })
        );

    }

    public Observable<List<Blog>> getBlog() {

        return Observable.create(emitter ->
                rootRef.collection(BLOG_COLLECTION)
                        .orderBy(HEAT_FIELD, Query.Direction.ASCENDING)
                        .limit(4)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (!emitter.isDisposed()) {
                                if (task.isSuccessful()) {
                                    emitter.onNext(task.getResult().toObjects(Blog.class));
                                } else{
                                    emitter.onError(task.getException());
                                }
                            }
                        }));

    }

    public Observable<List<Discussion>> getDiscussions(long limit) {

        return Observable.create(emitter -> {
            rootRef.collection(CHAT_ROOMS_COLLECTION)
                    .orderBy(LAST_MESSAGE_FIELD, Query.Direction.ASCENDING)
                    .whereArrayContains("participants", currentUser.getDisplayName())
                    .limit(limit)
                    .addSnapshotListener((queryDocumentSnapshots, e) -> {
                       if (!emitter.isDisposed()) {
                           if (e != null) emitter.onError(e);
                           else emitter.onNext(queryDocumentSnapshots.toObjects(Discussion.class));
                       }
                    });
        });

    }

    public void changeUserProfilePicture(Uri filePath, final String photoUrl) {

        StorageReference storageRef = storage.getReference("/users/" + auth.getCurrentUser().getUid() + "/" + UUID.randomUUID().toString() + ".jpg");
        StorageReference oldRef = storage.getReferenceFromUrl(photoUrl);

        storageRef.putFile(filePath)
                .addOnSuccessListener(taskSnapshot -> {

                    //delete old profile picture
                    oldRef.delete()
                            .addOnSuccessListener(aVoid -> Log.d(TAG, "addProfilePicture: old profile picture deleted"))
                            .addOnFailureListener(e -> Log.e(TAG, "addProfilePicture: onFailure: ", e));

                    //getting new Profile picture uri
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Log.d(TAG, "onSuccess: successfully uploaded: ");

                        profileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setPhotoUri(uri)
                                .build();
                        currentUser.updateProfile(profileChangeRequest);

                        //storing new profile picture on firestore db as downloadable url
                        DocumentReference documentReference = rootRef.collection("users").document(auth.getCurrentUser().getUid());
                        documentReference.update("photoUrl", uri.toString()).addOnSuccessListener(aVoid -> Log.d(TAG, "addProfilePicture: successfully added to database"));
                    });
                });

    }

}
