package com.incident.polyandroid.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class EventModel {

    public String title;
    public String section;
    public String locate;
    public String description;
    public String date;

    public EventModel() {

    }

    public EventModel(String title, String section, String locate, String description, String date) {
        this.title = title;
        this.section = section;
        this.locate = locate;
        this.description = description;
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("section", section);
        result.put("locate", locate);
        result.put("description", description);
        result.put("date", date);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[title," + title + "],[section," + section + "],[locate," + locate + "],[date," + date + "]";
    }

}
