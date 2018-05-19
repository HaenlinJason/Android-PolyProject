package com.incident.polyandroid;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.incident.polyandroid.models.EventModel;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "DEBUG_DB";

    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public DatabaseReference getFireBaseRoot() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public void pushNewsEvent(EventModel event) {
        String key = getFireBaseRoot().child("events").push().getKey();
        Log.d(TAG, "key :" + key);
        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put("/event/" + key, event.toMap());
        getFireBaseRoot().updateChildren(childUpdate);
    }

}
