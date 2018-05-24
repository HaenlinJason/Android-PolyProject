package com.incident.polyandroid.fragment;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.incident.polyandroid.R;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * Created by Noé MENARD on 12/05/2018.
 */

public class SortByListFragment extends Fragment {
    View rootView;

    public SortByListFragment(){

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            rootView = inflater.inflate(R.layout.sort_by_list, container, false);
        }

        else {
            rootView = inflater.inflate(R.layout.sort_by_list_horyzontale, container, false);
        }


        ImageButton button = (ImageButton) rootView.findViewById(R.id.SortByImportance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    EventListFragment fragment = new MyEventFragmentSortByImportance();
                    MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button2 = (ImageButton) rootView.findViewById(R.id.SortByNew);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragmentSortByDate();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.SortByDegradation);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragmentSortByDegradation();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button4 = (ImageButton) rootView.findViewById(R.id.SortByAll);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragment();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });


        ImageButton button5 = (ImageButton) rootView.findViewById(R.id.SortByObjets);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragmentSortByObjectLost();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button6 = (ImageButton) rootView.findViewById(R.id.SortByAutres);
        button6.setOnClickListener(new View.OnClickListener() {
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


