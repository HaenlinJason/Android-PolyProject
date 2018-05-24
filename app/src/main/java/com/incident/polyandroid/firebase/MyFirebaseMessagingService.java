package com.incident.polyandroid.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.incident.polyandroid.DetailledEventActivity;
import com.incident.polyandroid.MainActivity;
import com.incident.polyandroid.MyApplication;
import com.incident.polyandroid.R;
import com.incident.polyandroid.models.EventModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.incident.polyandroid.MainActivity.PREFS_NAME;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "DEBUGF";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.v(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0 && !remoteMessage.getData().get(getString(R.string.payload_id)).equals(((MyApplication) this.getApplication()).getIdLastCommit())) {
            Map<String, String> payload = remoteMessage.getData();
            Log.v(TAG, "Message data payload: " + remoteMessage.getData());

            String a = remoteMessage.getData().get("pictures_url");

            ArrayList<String> picture = new ArrayList<>();
            if (a == null)
            {
                picture = null;
            }
            else {
                try {
                    JSONArray jsonArray = new JSONArray(a);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        picture.add((String) jsonArray.get(i));
                    }

                    //System.out.printf(a);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            EventModel eventModel = new EventModel(
                    remoteMessage.getData().get("title"),
                    remoteMessage.getData().get("section"),
                    remoteMessage.getData().get("hurry"),
                    remoteMessage.getData().get("locate"),
                    remoteMessage.getData().get("description"),
                    remoteMessage.getData().get("date"),
                    picture
                    );

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

            Boolean silent = settings.getBoolean("Notification", true);


            if (silent) {
                buildNotification(eventModel);
            }
        }

        if (remoteMessage.getNotification() != null) {
            Log.v(TAG, "Message Notification Body: " + remoteMessage.getNotification());
        }
    }

    private void buildNotification(EventModel eventModel) {


        // Creates the PendingIntent

        List<String> list = new ArrayList<>();

        EventModel model = new EventModel("a", "b", "c", "d", "e","f",list);

        Intent intent = new Intent(this, DetailledEventActivity.class);
        intent.putExtra("event", eventModel);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(intent)
                .getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);//lkl


        //add properties to the builder
        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.polytechnotification)
                .setContentTitle("Un nouvel incident a été déclaré")
                .setContentText(eventModel.title)
                .setSound(alarmSound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(R.string.default_notification_channel_id, builder.build());
    }
}