package com.incident.polyandroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.incident.polyandroid.models.EventModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Polytech on 22/05/2018.
 */

    public class NotificationService extends Service {

        Timer timer;
        TimerTask timerTask;
        String TAG = "Timers";
        int Your_X_SECS = 5;
        Intent intent = null;


        @Override
        public IBinder onBind(Intent arg0)
        {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId){
            Log.e(TAG, "onStartCommand");
            super.onStartCommand(intent, flags, startId);

            startTimer();

            return START_STICKY;
        }


        @Override
        public void onCreate(){
            Log.e(TAG, "onCreate");


        }

        @Override
        public void onDestroy(){
            Log.e(TAG, "onDestroy");
            stoptimertask();
            super.onDestroy();


        }

        //we are going to use a handler to be able to run in our TimerTask
        final Handler handler = new Handler();


        public  void startTimer() {
            //set a new Timer
            timer = new Timer();

            //initialize the TimerTask's job
            initializeTimerTask();

            //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
            timer.schedule(timerTask, 5000, Your_X_SECS*1000); //
            //timer.schedule(timerTask, 5000,1000); //
        }

        public void stoptimertask() {
            //stop the timer, if it's not already null
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }

        public void initializeTimerTask() {

            timerTask = new TimerTask() {
                public void run() {

                    //use a handler to run a toast that shows the current timestamp
                    handler.post(new Runnable() {
                        public void run() {
                            notif();
                        }
                    });
                }
            };
        }


    public void notif(){
        //TODO CALL NOTIFICATION FUNC
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        List<String> list = new ArrayList<>();

        EventModel model = new EventModel("a", "b", "c", "d", "e",list);
        Intent intent = new Intent(this, DetailledEventActivity.class);
        intent.putExtra("event", model);
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(intent)
                .getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);


        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("ddd")
                .setContentText("aaa")
                .setContentIntent(pendingIntent);

        notificationManager.notify(1, builder.build());
    }
}
