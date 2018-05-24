package com.incident.polyandroid.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyEventFragmentSortByDegradation extends EventListFragment {

    public MyEventFragmentSortByDegradation() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All the eventsMyEventFragment
        Query query = databaseReference.child("events").orderByChild("section").equalTo("dégradation");
        //Query query = databaseReference.child("events").orderByChild("date").startAt().endAt();
        return query;
    }
}
