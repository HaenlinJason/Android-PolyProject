package com.incident.polyandroid;

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

        setContentView(R.layout.detailled_fragments_main);
        addImagesToThegallery(eventModel.pictures_url);
        ((TextView)findViewById(R.id.description)).setText(eventModel.description);
        ((TextView)findViewById(R.id.description)).setMovementMethod(new ScrollingMovementMethod());

        ((TextView)findViewById(R.id.type)).setText(eventModel.section);
        //((TextView)findViewById(R.id.urgence)).setText(eventModel.u);
        ((TextView)findViewById(R.id.lieu)).setText(eventModel.locate);
        ((TextView)findViewById(R.id.date)).setText(eventModel.date);
        ((TextView)findViewById(R.id.titre)).setText(eventModel.title);
    }

    private void addImagesToThegallery(final List<String> pictures) {
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
                addPictures(imageGallery, picture);
            }
        }
    }


    private View getImageView(Integer image) {
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(image);
        return imageView;
    }

    private void addPictures(LinearLayout imageGallery, final String picture){
        final ImageView imageView = new ImageView(this);
        final MyStorage storage = new MyStorage();
        storage.loadImageFromUrl(this, imageView, picture);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v1) {
                v1.setVisibility(View.INVISIBLE);
                ImageView view = (ImageView) findViewById(R.id.zoom);
                view.setVisibility(View.VISIBLE);
                storage.loadImageFromUrl(getBaseContext(), view, picture);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setVisibility(View.INVISIBLE);
                        v1.setVisibility(View.VISIBLE);
                    }
                });

            }
        });
        imageGallery.getLayoutParams().height = 600;
        imageGallery.getLayoutParams().width = 600;
        imageGallery.requestLayout();

        imageGallery.addView(imageView);
    }

}
