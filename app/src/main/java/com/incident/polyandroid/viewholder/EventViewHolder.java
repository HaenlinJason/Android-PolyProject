package com.incident.polyandroid.viewholder;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.stats.WakeLock;
import com.incident.polyandroid.R;
import com.incident.polyandroid.firebase.MyStorage;
import com.incident.polyandroid.models.EventModel;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView sectionView;
    private TextView titleView;
    private TextView locateView;
    private TextView dateView;
    private ImageView low;
    private ImageView mid;
    private ImageView high;
    private TextView photoCountView;

    private MyStorage storage;

    public EventViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.card_image_event);
        sectionView = itemView.findViewById(R.id.text_section_event);
        titleView = itemView.findViewById(R.id.text_title_event);
        locateView = itemView.findViewById(R.id.text_locate_event);
        dateView = itemView.findViewById(R.id.text_date_event);
        low = itemView.findViewById(R.id.imageView_gravity_1);
        mid = itemView.findViewById(R.id.imageView_gravity_2);
        high = itemView.findViewById(R.id.imageView_gravity_3);
        photoCountView = itemView.findViewById(R.id.textView3);

        storage = new MyStorage();

    }

    public void bindToEvent(EventModel event, Context context) {

        sectionView.setText(event.section);
        titleView.setText(event.title);
        locateView.setText(event.locate);
        dateView.setText(event.date);
        if (context.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            photoCountView.setVisibility(View.VISIBLE);
            if (event.pictures_url ==null){
                photoCountView.setText("Nombre de photos : "+"0");

            }
            else {
                photoCountView.setText("Nombre de photos : "+event.pictures_url.size());
            }
        }

        if (event.pictures_url == null)
            //storage.loadImageFromPath(context, imageView, "image/panda-kawaii-chibi.jpg");
            imageView.setImageResource(R.drawable.polytechnotification);
        else
            storage.loadImageFromUrl(context, imageView, event.pictures_url.get(0));

        if(event.hurry != null) {
            if (event.hurry.equals("mineur")) {
                lowGravity(context);
            } else if (event.hurry.equals("moyen")) {
                midGravity(context);
            } else {
                highGravity(context);
            }
        }
        else
            midGravity(context);

    }

    private void lowGravity(Context context){
        low.setVisibility(View.VISIBLE);
        low.setBackgroundColor(ContextCompat.getColor(context,R.color.low));
        mid.setVisibility(View.INVISIBLE);
        high.setVisibility(View.INVISIBLE);
    }

    private void midGravity(Context context){
        low.setVisibility(View.VISIBLE);
        low.setBackgroundColor(ContextCompat.getColor(context,R.color.mid));
        mid.setVisibility(View.VISIBLE);
        mid.setBackgroundColor(ContextCompat.getColor(context,R.color.mid));
        high.setVisibility(View.INVISIBLE);
    }

    private void highGravity(Context context){
        low.setVisibility(View.VISIBLE);
        low.setBackgroundColor(ContextCompat.getColor(context,R.color.high));
        mid.setVisibility(View.VISIBLE);
        mid.setBackgroundColor(ContextCompat.getColor(context,R.color.high));
        high.setVisibility(View.VISIBLE);
        high.setBackgroundColor(ContextCompat.getColor(context,R.color.high));
    }
}
