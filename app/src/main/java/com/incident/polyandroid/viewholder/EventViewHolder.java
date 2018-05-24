package com.incident.polyandroid.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incident.polyandroid.R;
import com.incident.polyandroid.firebase.MyStorage;
import com.incident.polyandroid.models.EventModel;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView sectionView;
    private TextView titleView;
    private TextView locateView;
    private TextView dateView;

    private MyStorage storage;

    public EventViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.card_image_event);
        sectionView = itemView.findViewById(R.id.text_section_event);
        titleView = itemView.findViewById(R.id.text_title_event);
        locateView = itemView.findViewById(R.id.text_locate_event);
        dateView = itemView.findViewById(R.id.text_date_event);

        storage = new MyStorage();

    }

    public void bindToEvent(EventModel event, Context context) {
        sectionView.setText(event.section);
        titleView.setText(event.title);
        locateView.setText(event.locate);
        dateView.setText(event.date);
        if (event.pictures_url == null)
            //storage.loadImageFromPath(context, imageView, "image/panda-kawaii-chibi.jpg");
            imageView.setImageResource(R.drawable.polytechnotification);
        else
            storage.loadImageFromUrl(context, imageView, event.pictures_url.get(0));
    }
}
