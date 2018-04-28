package com.incident.polyandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.incident.polyandroid.R;
import com.incident.polyandroid.database.FireBaseBasic;
import com.incident.polyandroid.models.EventModel;
import com.incident.polyandroid.viewholder.EventViewHolder;

public abstract class EventListFragment extends Fragment {

    private FireBaseBasic mDatabase;
    private FirebaseRecyclerAdapter<EventModel, EventViewHolder> mAdapter;
    private RecyclerView mRecycler;

    public EventListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_event, container, false);
        //init FireBaseBasic and get the reference to it
        mDatabase = new FireBaseBasic();
        //init the recyclerView with the container et set it to a fixed size
        mRecycler = rootView.findViewById(R.id.events_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up FireBaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase.getReference());

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<EventModel>()
                .setQuery(postsQuery, EventModel.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<EventModel, EventViewHolder>(options) {

            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull EventModel model) {

            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    public abstract Query getQuery(DatabaseReference databaseReference);
}
