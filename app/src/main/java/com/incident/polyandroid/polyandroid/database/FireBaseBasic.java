package com.incident.polyandroid.polyandroid.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseBasic {

    FirebaseDatabase database;
    DatabaseReference myRef;

    public FireBaseBasic(){
    }

    public void writeData(String ref, String msg){
        // Retrieve an instance of the database
        database = FirebaseDatabase.getInstance();
        // reference the location where I want to write to
        myRef  = database.getReference(ref);
        // write the data into the reference
        myRef.setValue(msg);
    }
}
