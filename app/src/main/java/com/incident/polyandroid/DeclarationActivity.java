package com.incident.polyandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;
import com.incident.polyandroid.models.EventModel;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class DeclarationActivity extends BaseActivity {
    private static final String TAG = "DEBUG_DB";

    private Integer compteur = 0;
    private List<String> dataLieu;
    private List<String> dataType;
    private List<String> dataImportance;
    private List<Bitmap> bitmap;

    Spinner lieuSpinner;
    Spinner importanceSpinner;
    Spinner typeSpinner;
    EditText eventTitle;
    EditText commentary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

        bitmap = new ArrayList<>();

        lieuSpinner = findViewById(R.id.spinnerLieu);
        importanceSpinner = findViewById(R.id.spinnerUrgence);
        typeSpinner = findViewById(R.id.spinnerType);

        dataLieu = new ArrayList<String>();
        dataLieu.add(getString(R.string.default_spinner));
        dataType = new ArrayList<String>();
        dataType.add(getString(R.string.default_spinner));
        dataImportance = new ArrayList<String>();
        dataImportance.add(getString(R.string.default_spinner));

        FloatingActionButton fab = findViewById(R.id.buttonValider);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("d MMM y à HH:mm", Locale.FRANCE);
                String time = sdf.format(Calendar.getInstance().getTime());
                if (!bitmap.isEmpty()) {
                    for (Bitmap img : bitmap) {
                        encodeBitmapAndSaveToFirebase(img);
                    }
                }
                String title = eventTitle.getText().toString();
                String comment = commentary.getText().toString();
                String lieu = lieuSpinner.getSelectedItem().toString();
                String importance = importanceSpinner.getSelectedItem().toString();
                String type = typeSpinner.getSelectedItem().toString();
                pushNewsEvent(new EventModel(title, type, lieu, comment, time));
                Toast toast = Toast.makeText(DeclarationActivity.this, R.string.toast_success_send, Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });

        getFireBaseRoot().child("enum").child("lieu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange : " + dataSnapshot.getValue());

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    dataLieu.add(singleSnapshot.getValue(String.class));
                }

                ArrayAdapter<String> lieuAdapter = new ArrayAdapter(DeclarationActivity.this, android.R.layout.simple_spinner_item, dataLieu);
                lieuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lieuSpinner.setAdapter(lieuAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        getFireBaseRoot().child("enum").child("importance").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange : " + dataSnapshot.getValue());

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    dataImportance.add(singleSnapshot.getValue(String.class));
                }

                Spinner lieuSpinner = findViewById(R.id.spinnerUrgence);
                ArrayAdapter<String> lieuAdapter = new ArrayAdapter(DeclarationActivity.this, android.R.layout.simple_spinner_item, dataImportance);
                lieuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lieuSpinner.setAdapter(lieuAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        getFireBaseRoot().child("enum").child("type").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange : " + dataSnapshot.getValue());

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    dataType.add(singleSnapshot.getValue(String.class));
                }

                Spinner lieuSpinner = findViewById(R.id.spinnerType);
                ArrayAdapter<String> lieuAdapter = new ArrayAdapter(DeclarationActivity.this, android.R.layout.simple_spinner_item, dataType);
                lieuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lieuSpinner.setAdapter(lieuAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ImageButton takePhoto = findViewById(R.id.buttonTakePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTakePhoto(v);
            }
        });

        eventTitle = findViewById(R.id.EditTextTitre);
        commentary = findViewById(R.id.EditTextCommentaire);


    }

    public void onClickTakePhoto(View v) {
        dispatchTakePictureIntent();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        if (compteur < 2) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Vous avez deja pris 2 photos";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (compteur == 0) {
            compteur++;
            ImageView image1 = findViewById(R.id.Image1);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                bitmap.add((Bitmap) extras.get("data"));
                image1.setImageBitmap(bitmap.get(compteur - 1));
            }
        } else if (compteur == 1) {
            compteur++;
            ImageView image2 = findViewById(R.id.Image2);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                bitmap.add((Bitmap) extras.get("data"));
                image2.setImageBitmap(bitmap.get(compteur - 1));
            }
        }
    }

    void encodeBitmapAndSaveToFirebase(Bitmap singlebitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        singlebitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = getStorageRoot().child("image/" + UUID.randomUUID().toString()).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d(TAG, "error while uploading the file", exception);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Log.d(TAG, "url : " + downloadUrl);
            }
        });

    }

    public void pushNewsEvent(EventModel event) {
        String key = getFireBaseRoot().child("events").push().getKey();
        Log.d(TAG, "key :" + key);
        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put("/events/" + key, event.toMap());
        getFireBaseRoot().updateChildren(childUpdate);
    }
}
