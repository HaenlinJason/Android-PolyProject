package com.incident.polyandroid.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.incident.polyandroid.R;
import com.incident.polyandroid.models.EventModel;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView sectionView;
    private TextView titleView;
    private TextView locateView;
    private TextView dateView;

    public EventViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.card_image_event);
        sectionView = itemView.findViewById(R.id.text_section_event);
        titleView = itemView.findViewById(R.id.text_title_event);
        locateView = itemView.findViewById(R.id.text_locate_event);
        dateView = itemView.findViewById(R.id.text_date_event);

    }

    public void bindToEvent(EventModel event, Context context) {
        sectionView.setText(event.section);
        titleView.setText(event.title);
        locateView.setText(event.locate);
        dateView.setText(event.date);
        if (event.image != null)
            Glide.with(context)
                    .load(event.image)
                    .fitCenter()
                    .into(imageView);
    }
}
