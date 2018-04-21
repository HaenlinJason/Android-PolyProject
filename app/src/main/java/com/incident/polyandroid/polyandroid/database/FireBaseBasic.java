package com.incident.polyandroid.polyandroid.database;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.incident.polyandroid.polyandroid.R;

public class FireBaseBasic {

    private static final String TAG = "DEBUG_DB";
    private String dataRead = "";
    FirebaseDatabase database;
    DatabaseReference myRef;

    public FireBaseBasic() {
    }

    public void writeSimpleData(String ref, String msg) {
        // Retrieve an instance of the database
        database = FirebaseDatabase.getInstance();
        // reference the location where I want to write to
        myRef = database.getReference(ref);
        // write the data into the reference
        myRef.setValue(msg);
    }

    public void subscribeSimpleData(String ref) {
        // Retrieve an instance of the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(ref);
        myRef.addListenerForSingleValueEvent(postListener);
    }

    public String getSimpleData(){
        return this.dataRead;
    }

    ValueEventListener postListener = new ValueEventListener() {
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
}
