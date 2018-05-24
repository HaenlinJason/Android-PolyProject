package com.incident.polyandroid;

import android.app.Application;

public class MyApplication extends Application{

    private String idLastCommit;
    private boolean enableNotification;

    /*
    * String s = ((MyApplication) this.getApplication()).setIdlastCommit();
    */
    public String getIdLastCommit() {
        return idLastCommit;
    }

    /**
     * ((MyApplication) this.getApplication()).getIdLastCommit("foo");
     * @param idLastCommit
     */
    public void setIdLastCommit(String idLastCommit) {
        this.idLastCommit = idLastCommit;
    }

    public boolean isEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        this.enableNotification = enableNotification;
    }
}
