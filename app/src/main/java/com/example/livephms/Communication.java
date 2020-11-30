package com.example.livephms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.SmsManager;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.livephms.ui.medication.Alarm;

public class Communication extends AppCompatActivity {
    public void pushNotification() {
        String title = "Time to take your " + R.id.dosage + " of " + R.id.medicationName + ".";
        String body = "Time to take your " + R.id.dosage + " of " + R.id.medicationName + ".\nLook for a " + R.id.medicationColor + " " + R.id.dosageType + ".";
        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification.Builder
                (getApplicationContext()).setContentText(body).setContentTitle(title).setSmallIcon(android.R.drawable.ic_dialog_info).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);
    }

    public void textSMS(String message) {
        try {
            SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
            String msg = sharedPref.getString(message, message);
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> msgArray = smsManager.divideMessage(msg);

            smsManager.sendMultipartTextMessage(message, null, msgArray, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Some fields is Empty", Toast.LENGTH_LONG).show();
        }
    }
}
