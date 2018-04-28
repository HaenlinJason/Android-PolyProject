package com.incident.polyandroid.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.incident.polyandroid.R;

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
}
