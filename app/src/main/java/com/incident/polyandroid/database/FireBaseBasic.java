package com.incident.polyandroid.database;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.incident.polyandroid.models.EventModel;

import java.util.HashMap;
import java.util.Map;

public class FireBaseBasic {

    private static final String TAG = "DEBUG_DB";
    private DatabaseReference mDatabase;

    public FireBaseBasic() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void pushNewsEvent(EventModel newEvent) {
        EventModel event = newEvent;
        String key = mDatabase.child("events").push().getKey();
        Log.d(TAG, "key :" + key);
        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put("/events/" + key, event.toMap());
        mDatabase.updateChildren(childUpdate);
    }

    /**
     * @param singleListen to True, will continue listening until the listener is stop
     */
    public void subscribeEventsData(boolean singleListen) {
        // retrieve the instance and get the reference
        mDatabase = FirebaseDatabase.getInstance().getReference("events");
        if (!singleListen)
            mDatabase.addListenerForSingleValueEvent(postListenerObject);
        else
            mDatabase.addValueEventListener(postListenerObject);
    }


    private ValueEventListener postListenerObject = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Get Post object and use the values to update the UI
            for (DataSnapshot var : dataSnapshot.getChildren()) {
                EventModel event = var.getValue(EventModel.class);
                Log.d(TAG, "value is: " + event.toString());
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            // ...
        }

    };

    public DatabaseReference getReference() {
        return mDatabase;
    }
}
