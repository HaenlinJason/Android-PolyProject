package com.incident.polyandroid.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.incident.polyandroid.MainActivity;
import com.incident.polyandroid.R;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "DEBUG_FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.v(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Map<String, String> payload = remoteMessage.getData();
            Log.v(TAG, "Message data payload: " + remoteMessage.getData());
            buildNotification();
        }

        if (remoteMessage.getNotification() != null) {
            Log.v(TAG, "Message Notification Body: " + remoteMessage.getNotification());
        }
    }

    private void buildNotification() {
        Intent pendingIntent = new Intent(this, MainActivity.class);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                getString(R.string.default_notification_channel_id));

        pendingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(this, 0, pendingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //add properties to the builder
        builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                        R.drawable.common_google_signin_btn_icon_dark))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle("test")
                .setAutoCancel(true)
                //.setSubText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("blablabla"))
                .setOnlyAlertOnce(true);

        builder.setContentIntent(notifyPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(R.string.default_notification_channel_id, builder.build());
    }
}