package com.incident.polyandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.widget.LinearLayout;

import com.incident.polyandroid.fragment.ImageAdapter;
import com.incident.polyandroid.fragment.ImageFragment;
import com.incident.polyandroid.models.EventModel;
import com.incident.polyandroid.viewholder.EventViewHolder;

/**
 * Created by Polytech on 14/05/2018.
 */

public class DetailledEventActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailld_fragments_main);
        //(LinearLayout)(R.layout.detailld_fragments_main)

        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        Fragment fragment = new ImageFragment();
        Fragment fragment2 = new ImageFragment();
        EventModel eventModel = new EventModel();
        Bundle bundle = new Bundle();
        fragMan.findFragmentById(R.id.fragmentLeft);

        /*bundle.putParcelable("bundle", eventModel);
        fragMan.findFragmentById(R.id.fragmentLeft).setArguments(eventModel);
        fragMan.findFragmentById(R.id.fragmentLeft) = fragment;*/

        /*findViewById(R.id.fragmentLeft) = fragment;

        fragTransaction.add(findViewById(R.id.fragmentLeft), fragment,"test ")*/




        //ViewPager viewPager = (ViewPager) getfindViewById(R.id.mvieww);
        //ImageAdapter adapter = new ImageAdapter(getBaseContext());
        //viewPager.setAdapter(adapter);
    }
}
