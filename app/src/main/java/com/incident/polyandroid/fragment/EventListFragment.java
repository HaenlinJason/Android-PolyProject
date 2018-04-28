package com.incident.polyandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final String TAG = "DEBUG_DB";

    private FireBaseBasic mDatabase;
    private FirebaseRecyclerAdapter<EventModel, EventViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private Context mContext;

    public EventListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "my fragment view created");
        View rootView = inflater.inflate(R.layout.fragment_all_event, container, false);
        mContext = this.getContext();
        //init FireBaseBasic and get the reference to it
        mDatabase = new FireBaseBasic("event");
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

            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
                Log.d(TAG, "view holder create");
                return new EventViewHolder(itemView);
            }

            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull EventModel model) {
                Log.d(TAG, "view holder bind");
                holder.bindToEvent(model, mContext);
            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "start");
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "stop");
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    public abstract Query getQuery(DatabaseReference databaseReference);
}
