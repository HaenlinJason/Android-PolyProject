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



        Drawable dr = getResources().getDrawable(R.drawable.urgent);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable urgent = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 330, true));

        Drawable dr2 = getResources().getDrawable(R.drawable.degradation);
        Bitmap bitmap2 = ((BitmapDrawable) dr2).getBitmap();
        Drawable degradation = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, 500, 330, true));

        Drawable dr3 = getResources().getDrawable(R.drawable.recent);
        Bitmap bitmap3 = ((BitmapDrawable) dr3).getBitmap();
        Drawable recent = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap3, 500, 330, true));

        Drawable dr4 = getResources().getDrawable(R.drawable.objets);
        Bitmap bitmap4 = ((BitmapDrawable) dr4).getBitmap();
        Drawable objets = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap4, 500, 330, true));

        Drawable dr5 = getResources().getDrawable(R.drawable.all);
        Bitmap bitmap5 = ((BitmapDrawable) dr5).getBitmap();
        Drawable all = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap5, 500, 330, true));

        Drawable dr6 = getResources().getDrawable(R.drawable.autres);
        Bitmap bitmap6 = ((BitmapDrawable) dr6).getBitmap();
        Drawable autres = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap6, 500, 330, true));

        ImageButton button_hurry = (ImageButton) rootView.findViewById(R.id.SortByImportance);
        button_hurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    EventListFragment fragment = new MyEventFragmentSortByImportance();
                    MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button_recent = (ImageButton) rootView.findViewById(R.id.SortByNew);
        button_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragmentSortByDate();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button_decay = (ImageButton) rootView.findViewById(R.id.SortByDegradation);
        button_decay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragmentSortByDegradation();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button_all = (ImageButton) rootView.findViewById(R.id.SortByAll);
        button_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragment();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button_objets = (ImageButton) rootView.findViewById(R.id.SortByObjets);
        button_objets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventListFragment fragment = new MyEventFragmentSortByObjectLost();
                MoveToAppropriateMyEventFragment(fragment);
            }
        });

        ImageButton button_others = (ImageButton) rootView.findViewById(R.id.SortByAutres);
        button_others.setOnClickListener(new View.OnClickListener() {
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


