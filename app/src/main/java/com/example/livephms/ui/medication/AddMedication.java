package com.example.livephms.ui.medication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.livephms.R;

public class AddMedication extends Fragment {
    public static final String PREFS_Medication = "sharedMedicationPrefs";
    public static final String NAME_INPUT = "medicationName";
    public static final String DOSAGE_INPUT = "dosage";
    public static final String DOSAGE_TYPE_INPUT = "dosageType";
    public static final String FREQUENCY = "frequency";

    private EditText medicationName;
    private EditText dosage;
    private EditText dosageType;
    private EditText frequency;
    private Button medicationSubmit, viewSavedMedication, addAlarm, viewSavedAlarms;

    private String loadMedicationName, loadDosage, loadDosageType, loadFrequency, loadAlarm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_medication, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medicationName = (EditText) getActivity().findViewById(R.id.medicationName);
        dosage = (EditText) getActivity().findViewById(R.id.dosage);
        dosageType = (EditText) getActivity().findViewById(R.id.dosageType);
        frequency = (EditText) getActivity().findViewById(R.id.frequency);
        medicationSubmit = (Button) getActivity().findViewById(R.id.medicationSubmit);
        viewSavedMedication = (Button) getActivity().findViewById(R.id.viewSavedMedication);
        addAlarm = (Button) getActivity().findViewById(R.id.addAlarm);
        viewSavedAlarms = (Button) getActivity().findViewById(R.id.viewSavedAlarms);

        medicationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicationName.setText(medicationName.getText().toString());
                dosage.setText(dosage.getText().toString());
                dosageType.setText(dosageType.getText().toString());
                frequency.setText(frequency.getText().toString());
                saveMedicationData();

            }
        });

        viewSavedMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_medication_intent = new Intent(getActivity().getApplicationContext(), SavedMedication.class);
                startActivity(add_medication_intent);
            }
        });

        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_medication_alarm_intent = new Intent(getActivity().getApplicationContext(), Alarm.class);
                startActivity(add_medication_alarm_intent);
            }
        });

        viewSavedAlarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saved_alarms_intent = new Intent(getActivity().getApplicationContext(), Alarm.class);
                startActivity(saved_alarms_intent);
            }
        });

        loadMedicationData();
        updateMedicationData();

    }

    private void saveMedicationData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_Medication, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(NAME_INPUT, medicationName.getText().toString());
        editor.putString(DOSAGE_INPUT, dosage.getText().toString());
        editor.putString(DOSAGE_TYPE_INPUT, dosageType.getText().toString());
        editor.putString(FREQUENCY, frequency.getText().toString());

        editor.apply();
        Toast.makeText(getActivity().getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadMedicationData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_Medication, Context.MODE_MULTI_PROCESS);
        loadMedicationName = sharedPreferences.getString(NAME_INPUT, "");
        loadDosage = sharedPreferences.getString(DOSAGE_INPUT, "");
        loadDosageType = sharedPreferences.getString(DOSAGE_TYPE_INPUT, "");
        loadFrequency = sharedPreferences.getString(FREQUENCY, "");
    }

    public void updateMedicationData(){
        medicationName.setText(loadMedicationName);
        dosage.setText(loadDosage);
        dosageType.setText(loadDosageType);
        frequency.setText(loadFrequency);
    }
}
