package com.incident.polyandroid.firebase;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.incident.polyandroid.models.EventModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDatabase {

    private static final String TAG = "DEBUG_DB";
    private DatabaseReference mDatabase;
    private String post;

    public MyDatabase(String post) {
        this.post = post;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public MyDatabase() {
        this.post = "";
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void pushNewsEvent(EventModel newEvent) {
        EventModel event = newEvent;
        String key = mDatabase.child(post).push().getKey();
        Log.d(TAG, "key :" + key);
        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put("/" + post + "/" + key, event.toMap());
        mDatabase.updateChildren(childUpdate);
    }

    public void subscribeToChildEvent() {
        mDatabase.addChildEventListener(childEventListener);
    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(TAG, "postComments:onCancelled", databaseError.toException());

        }
    };

    public DatabaseReference getReference() {
        return mDatabase;
    }

    public void cleanupListener() {

        if (childEventListener != null)
            mDatabase.removeEventListener(childEventListener);
    }
}
