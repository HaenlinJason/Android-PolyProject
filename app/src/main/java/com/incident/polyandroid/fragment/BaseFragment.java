package com.incident.polyandroid.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaseFragment extends Fragment {
    private ProgressDialog mProgressDialog;

    public void showProgressDialog(Context context) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }
        timerDelayRemoveDialog(2500,context);
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

    public void timerDelayRemoveDialog(long time, final Context context){
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mProgressDialog.dismiss();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, "AUCUN INCIDENT A VISUALISER", duration);
                toast.show();
            }
        }, time);
    }
}
