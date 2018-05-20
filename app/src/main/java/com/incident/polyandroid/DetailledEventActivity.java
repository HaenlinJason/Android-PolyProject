package com.incident.polyandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incident.polyandroid.models.EventModel;

/**
 * Created by Polytech on 14/05/2018.
 */

public class DetailledEventActivity extends AppCompatActivity {
    private Integer images[] = {R.drawable.common_google_signin_btn_text_light_focused, R.drawable.common_google_signin_btn_text_light_focused, R.drawable.common_google_signin_btn_icon_dark_normal,
            R.drawable.common_google_signin_btn_text_light_focused,R.drawable.common_google_signin_btn_text_light_focused,R.drawable.common_google_signin_btn_text_light_focused};

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
        addImagesToThegallery();
        ((TextView)findViewById(R.id.description)).setText(eventModel.description);
        ((TextView)findViewById(R.id.description)).setMovementMethod(new ScrollingMovementMethod());

        ((TextView)findViewById(R.id.type)).setText(eventModel.section);
        //((TextView)findViewById(R.id.urgence)).setText(eventModel.u);
        ((TextView)findViewById(R.id.lieu)).setText(eventModel.locate);
        ((TextView)findViewById(R.id.date)).setText(eventModel.date);
        ((TextView)findViewById(R.id.titre)).setText(eventModel.title);
    }

    private void addImagesToThegallery() {
        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.imageGallery);
        for (Integer image : images) {
            imageGallery.addView(getImageView(image));
        }
    }


    private View getImageView(Integer image) {
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 10, 0);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(image);
        return imageView;
    }

}
