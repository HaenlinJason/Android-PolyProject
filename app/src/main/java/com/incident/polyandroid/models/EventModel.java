package com.incident.polyandroid.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IgnoreExtraProperties
public class EventModel implements Parcelable {

    public String title;
    public String section;
    public String locate;
    public String description;
    public String hurry;
    public String date;
    public List<String> pictures_url;

    public EventModel() {

    }

    public EventModel(String title, String section, String hurry,String locate, String description, String date, List<String> pictures_url) {
        this.title = title;
        this.section = section;
        this.locate = locate;
        this.description = description;
        this.date = date;
        this.hurry = hurry;
        this.pictures_url = pictures_url;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("section", section);
        result.put("locate", locate);
        result.put("hurry", hurry);
        result.put("description", description);
        result.put("date", date);
        result.put("pictures_url", pictures_url);
        return result;
    }

    @Exclude
    @Override
    public String toString() {
        return getClass().getSimpleName() + "=[TITLE: " + title + "],[SECTION: " + section + "],[HURRY, "+hurry+"],[LOCATE: " + locate + "],[DATE: " + date + "],[URLS: "+pictures_url+"]";
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

        @Override
        public void writeToParcel(Parcel dest, int flags)
        {
            dest.writeString(title);
            dest.writeString(section);
            dest.writeString(locate);
            dest.writeString(hurry);
            dest.writeString(description);
            dest.writeString(date);
            dest.writeList(pictures_url);
        }

        public static final Parcelable.Creator<EventModel> CREATOR = new Parcelable.Creator<EventModel>()
        {
            @Override
            public EventModel createFromParcel(Parcel source)
            {
                return new EventModel(source);
            }

            @Override
            public EventModel[] newArray(int size)
            {
                return new EventModel[size];
            }
        };

    public EventModel(Parcel in) {
            this.title = in.readString();
            this.section = in.readString();
            this.locate = in.readString();
            this.hurry = in.readString();
            this.description = in.readString();
            this.date = in.readString();
            this.pictures_url = in.readArrayList(null);
    }

}
