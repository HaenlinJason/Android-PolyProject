package com.incident.polyandroid;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.incident.polyandroid.firebase.MyStorage;
import com.incident.polyandroid.models.EventModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * Created by Polytech on 14/05/2018.
 */

public class DetailledEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventModel eventModel = new EventModel();
        try {
            eventModel = getIntent().getExtras().getParcelable("event");

        }
        catch (Exception e){

        }

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        setContentView(R.layout.detailled_fragments_main);
        else {
            setContentView(R.layout.detailled_fragments_main_horyzontale);
        }
        addImagesToThegallery(eventModel.pictures_url/*, this.getResources().getConfiguration().orientation*/);
        ((TextView)findViewById(R.id.description)).setText(eventModel.description);
        ((TextView)findViewById(R.id.description)).setMovementMethod(new ScrollingMovementMethod());

        ((TextView)findViewById(R.id.type)).setText(eventModel.section);
        ((TextView)findViewById(R.id.urgence)).setText("Gravité : " + eventModel.hurry);
        ((TextView)findViewById(R.id.lieu)).setText(eventModel.locate);
        ((TextView)findViewById(R.id.date)).setText(eventModel.date);
        ((TextView)findViewById(R.id.titre)).setText(eventModel.title);
    }

    private void addImagesToThegallery(final List<String> pictures/*, int orientation*/) {
        //LinearLayout imageGallery = (LinearLayout) findViewById(R.id.imageGallery);
        LinearLayout imageGallery = (LinearLayout ) findViewById(R.id.imageGallery);
        if (pictures == null) {
            imageGallery.getLayoutParams().height = 600;
            imageGallery.getLayoutParams().width = 600;
            imageGallery.requestLayout();
            imageGallery.addView(getImageView(R.drawable.polytechnotification));
        }
        else
        {
            for (final String picture:pictures) {
                imageGallery.getLayoutParams().height = 10;
                //imageGallery.getLayoutParams().width = 20;
                imageGallery.requestLayout();
                addPictures(imageGallery, picture/*, orientation*/);
            }
        }
    }


    private View getImageView(Integer image) {
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, 0);
        //lp.setMargins(0, 0, 0, 0);
        imageView.setImageResource(image);
        imageView.setLayoutParams(lp);
        //imageView.setImageResource(image);
        return imageView;
    }

    private void addPictures(LinearLayout imageGallery, final String picture/*, final int orientation*/){
        final ImageView imageView = new ImageView(this);
        final MyStorage storage = new MyStorage();
        storage.loadImageFromUrl(this, imageView, picture);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 1000);
        lp.setMargins(0, 0, 50, 0);
        imageView.setLayoutParams(lp);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v1) {
                findViewById(R.id.imageGallery).setVisibility(View.INVISIBLE);
                ((TextView)findViewById(R.id.description)).setVisibility(View.INVISIBLE);
                ((TextView)findViewById(R.id.descriptionLabel)).setVisibility(View.INVISIBLE);
                ((TextView)findViewById(R.id.type)).setVisibility(View.INVISIBLE);
                ((TextView)findViewById(R.id.urgence)).setVisibility(View.INVISIBLE);
                ((TextView)findViewById(R.id.lieu)).setVisibility(View.INVISIBLE);
                ((TextView)findViewById(R.id.date)).setVisibility(View.INVISIBLE);
                ((TextView)findViewById(R.id.titre)).setVisibility(View.INVISIBLE);


                ImageView view = (ImageView) findViewById(R.id.zoom);
                view.setVisibility(View.VISIBLE);
/*
                if (orientation == Configuration.ORIENTATION_PORTRAIT){
                    view.getLayoutParams().height =650;
                    view.getLayoutParams().width =600;
                }
                else {
                    view.getLayoutParams().height =650;
                    view.getLayoutParams().width =450;
                }*/


                storage.loadImageFromUrl(getBaseContext(), view, picture);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        v.setVisibility(View.INVISIBLE);

                        findViewById(R.id.imageGallery).setVisibility(View.VISIBLE);
                        ((TextView)findViewById(R.id.description)).setVisibility(View.VISIBLE);
                        ((TextView)findViewById(R.id.descriptionLabel)).setVisibility(View.VISIBLE);
                        ((TextView)findViewById(R.id.type)).setVisibility(View.VISIBLE);
                        ((TextView)findViewById(R.id.urgence)).setVisibility(View.VISIBLE);
                        ((TextView)findViewById(R.id.lieu)).setVisibility(View.VISIBLE);
                        ((TextView)findViewById(R.id.date)).setVisibility(View.VISIBLE);
                        ((TextView)findViewById(R.id.titre)).setVisibility(View.VISIBLE);
                    }
                });

            }
        });
        imageGallery.getLayoutParams().height = 550;
        //imageGallery.getLayoutParams().width = 20;
        imageGallery.requestLayout();

        imageGallery.addView(imageView);
    }

}
