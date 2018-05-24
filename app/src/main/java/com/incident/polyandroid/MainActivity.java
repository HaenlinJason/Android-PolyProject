package com.incident.polyandroid;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.incident.polyandroid.firebase.MyDatabase;
import com.incident.polyandroid.firebase.MyStorage;
import com.incident.polyandroid.fragment.MyEventFragment;
import com.incident.polyandroid.fragment.SortByListFragment;
import com.incident.polyandroid.models.EventModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "DEBUG_DB";
    public static final String PREFS_NAME = "MyPrefsFile";
    Spinner sortLieu;
    List<String> sortLieuArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
            /*NotificationService notificationService = new NotificationService();
        notificationService.notif();*/

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Notification", "true");
        editor.apply();
        String silent = settings.getString("Notification", "false");

        Log.d(TAG, " Notification Status : " + silent);

        FirebaseMessaging.getInstance().subscribeToTopic("events");

        super.onCreate(savedInstanceState);
        hideProgressDialog();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDeclarationActivity(view);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        sortLieu = findViewById(R.id.spinnerSortLieu);
        sortLieuArray = new ArrayList<>();
        sortLieuArray.add(getString(R.string.default_spinner));


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //MyEventFragment fragment = new MyEventFragment();
            SortByListFragment fragment = new SortByListFragment();
            //fragmentTransaction.add(R.id.main_content_fragment, fragment);
            fragmentTransaction.add(R.id.main_content_fragment, fragment);
            fragmentTransaction.commit();
        }

        getFireBaseRoot().child("enum").child("lieu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange : " + dataSnapshot.getValue());

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    sortLieuArray.add(singleSnapshot.getValue(String.class));
                }

                ArrayAdapter<String> lieuAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, sortLieuArray);
                lieuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sortLieu.setAdapter(lieuAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Log.d(TAG,"nav heay");
        if (id == R.id.toggleButton_notification_activation) {
            Log.d(TAG,"CLICK2");
        } else if (id == R.id.nav_gallery) {
            Log.d(TAG,"CLICK3");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    public void createDeclarationActivity(View view) {
        Intent intent = new Intent(this, DeclarationActivity.class);
        startActivity(intent);
    }
}
