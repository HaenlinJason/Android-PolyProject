package com.incident.polyandroid.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Event {

    public String title;
    public String type;
    public String locate;
    public String description;
    public String date;

    public Event() {

    }

    public Event(String title, String type, String locate, String description, String date) {
        this.title = title;
        this.type = type;
        this.locate = locate;
        this.description = description;
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("type", type);
        result.put("locate", locate);
        result.put("description", description);
        result.put("date", date);
        return result;
    }

}
