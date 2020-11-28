package com.example.livephms.ui.vital_signs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.livephms.R;

public class SavedData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_data);
        TextView savedBloodPressure;
        TextView savedCholesterol;
        TextView savedGlucose;
        TextView savedHeartRate;
        TextView savedTemperature;
        savedBloodPressure = findViewById(R.id.savedBloodPressure);
        savedCholesterol = findViewById(R.id.savedCholesterol);
        savedGlucose = findViewById(R.id.savedGlucose);
        savedHeartRate = findViewById(R.id.savedHeartRate);
        savedTemperature = findViewById(R.id.savedTemperature);

        SharedPreferences bloodPressurePrefs = getApplicationContext().getSharedPreferences("sharedBloodPressurePrefs", Context.MODE_PRIVATE);
        //bloodPressurePrefs.edit().clear().commit();
        String systolicInput = bloodPressurePrefs.getString("systolic", "");
        String diastolicInput = bloodPressurePrefs.getString("diastolic", "");
        String dateMeasured = bloodPressurePrefs.getString("date", "");
        savedBloodPressure.setText("Systolic: "+ systolicInput +"\n"+ "Diastolic: " + diastolicInput +"\n"+ "Date: " + dateMeasured);



        SharedPreferences cholesterolPrefs = getApplicationContext().getSharedPreferences("sharedCholesterolPrefs", Context.MODE_PRIVATE);
        String cholesterolInput = cholesterolPrefs.getString("cholesterol", "");
        String hdlInput = cholesterolPrefs.getString("hdl", "");
        String ldlInput = cholesterolPrefs.getString("hdl", "");
        String triglyceridesInput = cholesterolPrefs.getString("triglycerides", "");
        savedCholesterol.setText("\n" + "Cholesterol: " + cholesterolInput +"\n"+ "HDL: " + hdlInput +"\n"+ "LDL: " + ldlInput +"\n"+ "Triglycerides: " + triglyceridesInput);
        //cholesterolPrefs.edit().clear().commit();

        SharedPreferences glucosePrefs = getApplicationContext().getSharedPreferences("sharedGlucosePrefs", Context.MODE_PRIVATE);
        String glucoseInput = glucosePrefs.getString("glucose", "");
        savedGlucose.setText("\n" + "Glucose: " + glucoseInput);
        //glucosePrefs.edit().clear().commit();

        SharedPreferences heartRatePrefs = getApplicationContext().getSharedPreferences("sharedHeartRatePrefs", Context.MODE_PRIVATE);
        String heartInput = heartRatePrefs.getString("heartRate", "");
        savedHeartRate.setText("\n" + "Heart Rate: " + heartInput);
        //heartRatePrefs.edit().clear().commit();

        SharedPreferences temperaturePrefs = getApplicationContext().getSharedPreferences("sharedTemperaturePrefs", Context.MODE_PRIVATE);
        String temperatureInput = temperaturePrefs.getString("temperature" , "");
        savedTemperature.setText("\n" + "Temperature: " + temperatureInput);
        //temperaturePrefs.edit().clear().commit();

    }
}
