package com.incident.polyandroid.fragment;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyEventFragment extends EventListFragment {

    public MyEventFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All the eventsMyEventFragment
        return databaseReference.child("events");
    }
}
