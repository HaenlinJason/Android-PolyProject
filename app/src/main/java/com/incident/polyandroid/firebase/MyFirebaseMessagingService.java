package com.incident.polyandroid.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.incident.polyandroid.DetailledEventActivity;
import com.incident.polyandroid.MainActivity;
import com.incident.polyandroid.MyApplication;
import com.incident.polyandroid.R;
import com.incident.polyandroid.models.EventModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "DEBUG_FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.v(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0 && !remoteMessage.getData().get(getString(R.string.payload_id)).equals(((MyApplication) this.getApplication()).getIdLastCommit())) {
            Map<String, String> payload = remoteMessage.getData();
            Log.v(TAG, "Message data payload: " + remoteMessage.getData());
            buildNotification();
        }

        if (remoteMessage.getNotification() != null) {
            Log.v(TAG, "Message Notification Body: " + remoteMessage.getNotification());
        }
    }

    private void buildNotification() {


        // Creates the PendingIntent

        List<String> list = new ArrayList<>();

        EventModel model = new EventModel("a", "b", "c", "d", "e",list);

        Intent intent = new Intent(this, DetailledEventActivity.class);
        intent.putExtra("event", model);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(intent)
                .getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);//lkl


        //add properties to the builder
        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.polytechnotification)
                .setContentTitle("ddd")
                .setContentText("aaa")
                .setSound(alarmSound)
                .setContentIntent(pendingIntent);


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(R.string.default_notification_channel_id, builder.build());
    }
}