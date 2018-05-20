package com.incident.polyandroid.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyEventFragmentSortByImportance extends EventListFragment {

    public MyEventFragmentSortByImportance() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All the eventsMyEventFragment
        Query query = databaseReference.child("events").orderByChild("title").equalTo("Laptop perdu");
        //Query query = databaseReference.child("events").orderByChild("date").startAt().endAt();
        return query;
    }
}
