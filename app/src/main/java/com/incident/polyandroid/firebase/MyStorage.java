package com.incident.polyandroid.firebase;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class MyStorage {

    private final static String TAG = "DEBUG_STORAGE";

    private StorageReference mStorageRef;

    public MyStorage() {
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void uploadFile(String path) {
        Uri file = Uri.fromFile(new File(path));
        StorageReference riversRef = mStorageRef.child("images/" + file.getLastPathSegment());

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Log.d(TAG, "url : " + downloadUrl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.d(TAG, "error while uploading the file", exception);
                    }
                });
    }
}
