package com.example.livephms.ui.medication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.livephms.MoniteringSystemAndAlerts;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String PREFS_Alarm = "sharedAlarmPrefs";
    public static final String ID = "alarmID";
    private static final String CHANNEL_ID = "CHANNEL_SAMPLE";

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get id & message
        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("message");

        // Call MainActivity when notification is tapped.
        Intent mainIntent = new Intent(context, Alarm.class);

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_Alarm, Context.MODE_PRIVATE);
        int id = Integer.parseInt(sharedPreferences.getString(ID, ""));
        PendingIntent contentIntent = PendingIntent.getActivity(context, id, mainIntent, 0);

        // NotificationManager
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // For API 26 and above
            CharSequence channelName = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }

        // Prepare Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("TITLE")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Notify
        notificationManager.notify(notificationId, builder.build());
        MoniteringSystemAndAlerts MSA = new MoniteringSystemAndAlerts();
        MSA.medConsumptionAlert();
    }
}

