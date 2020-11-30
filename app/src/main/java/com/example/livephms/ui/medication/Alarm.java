package com.example.livephms.ui.medication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.livephms.R;

import java.util.Calendar;


public class Alarm extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_Alarm = "sharedAlarmPrefs";
    public static final String ID = "alarmID";
    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        findViewById(R.id.setButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        EditText medName = findViewById(R.id.medName);
        TimePicker timePicker = findViewById(R.id.timePicker);

        // Intent
        Intent intent = new Intent(com.example.livephms.ui.medication.Alarm.this, com.example.livephms.ui.medication.AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("message", medName.getText().toString());

        final int id = (int)System.currentTimeMillis();

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_Alarm, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ID, String.valueOf(id));
        editor.apply();

        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                com.example.livephms.ui.medication.Alarm.this, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setButton:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelButton:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}




