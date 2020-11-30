package com.example.livephms.ui.medication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.livephms.R;

public class SavedMedication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_medication);
        TextView savedMedication;

        savedMedication = findViewById(R.id.savedMedication);

        SharedPreferences medicationPrefs = getApplicationContext().getSharedPreferences("sharedMedicationPrefs", Context.MODE_PRIVATE);
        //medicationPrefs.edit().clear().commit();
        String medicationName = medicationPrefs.getString("medicationName", "");
        String dosage = medicationPrefs.getString("dosage", "");
        String dosageType = medicationPrefs.getString("dosageType", "");
        String frequency = medicationPrefs.getString("frequency", "");
        savedMedication.setText("Medication Name: "+ medicationName +"\n"+ "Dosage: " + dosage +"\n"+ "Dosage Type: " + dosageType +"Frequency: " + frequency);



    }
}
