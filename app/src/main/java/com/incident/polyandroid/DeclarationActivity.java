package com.incident.polyandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.incident.polyandroid.models.EventModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DeclarationActivity extends BaseActivity {
    private static final String TAG = "DEBUG_DB";

    private Integer compteur = 0;
    private List<String> dataLieu;
    private List<String> dataType;
    private List<String> dataImportance;

    Spinner lieuSpinner;
    Spinner importanceSpinner;
    Spinner typeSpinner;
    EditText eventTitle;
    EditText commentary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

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
                String title = eventTitle.getText().toString();
                String comment = commentary.getText().toString();
                String lieu = lieuSpinner.getSelectedItem().toString();
                String importance = importanceSpinner.getSelectedItem().toString();
                String type = typeSpinner.getSelectedItem().toString();
                pushNewsEvent(new EventModel(title,type,lieu,comment, Calendar.getInstance().getTime().toString()));
                Toast toast = Toast.makeText(DeclarationActivity.this, R.string.toast_success_send, Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });

        getFireBaseRoot().child("enum").child("lieu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange : " + dataSnapshot.getValue());

                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
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
        getFireBaseRoot().child("enum").child("importance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange : " + dataSnapshot.getValue());

                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
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
        getFireBaseRoot().child("enum").child("type").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange : " + dataSnapshot.getValue());

                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
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

        Button takePhoto = findViewById(R.id.buttonTakePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener()
        {
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
            compteur = 1;
            ImageView image1 = findViewById(R.id.Image1);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                image1.setImageBitmap(imageBitmap);
            }
        } else if (compteur == 1) {
            compteur = 2;
            ImageView image2 = findViewById(R.id.Image2);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                image2.setImageBitmap(imageBitmap);
            }
        }
    }
}
