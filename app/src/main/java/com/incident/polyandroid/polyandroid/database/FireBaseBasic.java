package com.incident.polyandroid.polyandroid.database;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.incident.polyandroid.polyandroid.model.User;

public class FireBaseBasic {

    private static final String TAG = "DEBUG_DB";
    private String dataRead = "";
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;

    public FireBaseBasic() {
    }

    public void writeSimpleData(String ref, String msg) {
        // Retrieve an instance of the mDatabase
        mDatabase = FirebaseDatabase.getInstance();
        // reference the location where I want to write to
        myRef = mDatabase.getReference(ref);
        // write the data into the reference
        myRef.setValue(msg);
    }

    public void writeNewUser(String userId, String name, String email) {

        User user = new User(name, email);
        // retrieve the instance and get the reference
        myRef = FirebaseDatabase.getInstance().getReference();
        // write the object into the data base with the reference "users" and with the index of the user.
        myRef.child("users").child(userId).setValue(user);
    }

    public void subscribeSimpleData(String ref) {
        // Retrieve an instance of the mDatabase
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference(ref);
        myRef.addListenerForSingleValueEvent(postListenerSimpleData);
    }

    public void subscribeUserData(String index) {
        // retrieve the instance and get the reference
        myRef = FirebaseDatabase.getInstance().getReference("users").child(index);
        myRef.addListenerForSingleValueEvent(postListenerUserObject);
    }


    public String getSimpleData() {
        return this.dataRead;
    }

    private ValueEventListener postListenerSimpleData = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            dataRead = dataSnapshot.getValue(String.class);
            Log.d(TAG, "Value is: " + dataRead);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    };

    private ValueEventListener postListenerUserObject = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Get Post object and use the values to update the UI
            User user = dataSnapshot.getValue(User.class);
            Log.d(TAG,"value is: "+user.toString());
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };
}
