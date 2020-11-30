package com.example.livephms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.widget.Toast;
import java.util.ArrayList;

public class Communication extends AppCompatActivity {
    public void pushNotification(String message) {

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
