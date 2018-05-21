package com.incident.polyandroid.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.incident.polyandroid.R;

/**
 * Created by Noé MENARD on 12/05/2018.
 */

public class SortByListFragment extends Fragment {
    public SortByListFragment(){

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.sort_by_list, container, false);

        Button button = (Button) rootView.findViewById(R.id.SortByImportance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    EventListFragment fragment = new MyEventFragmentSortByImportance();
                    MoveToAppropriateMyEventFragment(fragment);
            }
        });

        Button button2 = (Button) rootView.findViewById(R.id.SortByNew);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragment();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        Button button3 = (Button) rootView.findViewById(R.id.SortByDegradation);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragmentSortByDegradation();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        Button button4 = (Button) rootView.findViewById(R.id.SortByAll);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragmentSortByImportance();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void onButtonClickCancel(View view) {
    }

    public void MoveToAppropriateMyEventFragment(EventListFragment myEventFragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content_fragment, myEventFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}


