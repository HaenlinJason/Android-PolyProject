package com.incident.polyandroid.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Polytech on 21/05/2018.
 */

public class MyEventFragmentSortByObjectLost extends EventListFragment {

    public MyEventFragmentSortByObjectLost() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All the eventsMyEventFragment
        Query query = databaseReference.child("events").orderByChild("section").equalTo("objet perdu");
        //Query query = databaseReference.child("events").orderByChild("date").startAt().endAt();
        return query;
    }
}
