package com.incident.polyandroid.models;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class DataModel {

    public List<String> importance;
    public List<String> lieu;
    public List<String> type;

    public DataModel() {

    }

    public DataModel(List<String> importance, List<String> lieu, List<String> type){
        this.importance = importance;
        this.lieu = lieu;
        this.type = type;
    }


    @Exclude
    @Override
    public String toString() {
        return "DataModel{" +
                "importance=" + importance +
                ", lieu=" + lieu +
                ", type=" + type +
                '}';
    }
}
