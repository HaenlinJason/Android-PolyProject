package com.incident.polyandroid.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyEventFragmentSortByDate extends EventListFragment {
    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All the eventsMyEventFragment
        return databaseReference.child("events").limitToLast(10);

    }
}
