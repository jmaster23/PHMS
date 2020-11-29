package com.example.livephms.ui.vital_signs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.livephms.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BloodPressure extends Fragment {
    public static final String PREFS_BloodPressure = "sharedBloodPressurePrefs";
    public static final String SYSTOLIC_INPUT = "systolic";
    public static final String DIASTOLIC_INPUT = "diastolic";
    public static final String DATE_MEASURED = "date";

    private EditText systolicInput;
    private EditText diastolicInput;
    private EditText dateMeasured;
    private TextView checkSafeLevels;
    private Button bloodPressureSubmit, viewSavedBloodPressure;


   private String loadSystolic, loadDiastolic, loadDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_pressure,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        systolicInput = (EditText) getActivity().findViewById(R.id.systolicInput);
        diastolicInput = (EditText) getActivity().findViewById(R.id.diastolicInput);
        dateMeasured = (EditText) getActivity().findViewById(R.id.dateMeasured);
        checkSafeLevels = (TextView) getActivity().findViewById(R.id.checkSafeLevels);

        bloodPressureSubmit = (Button) getActivity().findViewById(R.id.bloodPressureSubmit);
        viewSavedBloodPressure = (Button) getActivity().findViewById(R.id.viewSavedBloodPressure);
        viewSavedBloodPressure = (Button) getActivity().findViewById(R.id.viewSavedBloodPressure);

        bloodPressureSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                systolicInput.setText(systolicInput.getText().toString());
                diastolicInput.setText(diastolicInput.getText().toString());
                dateMeasured.setText(dateMeasured.getText().toString());
                checkSafeLevels();
                saveBloodPressureData();

            }
        });
        viewSavedBloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blood_pressure_intent = new Intent(getActivity().getApplicationContext(), com.example.livephms.ui.vital_signs.SavedData.class);
                startActivity(blood_pressure_intent);
            }
        });

        loadBloodPressureData();
        updateBloodPressureData();

    }

    private void checkSafeLevels(){
        int sys = (Integer.valueOf(systolicInput.getText().toString()));
        int dias = (Integer.valueOf(diastolicInput.getText().toString()));
        if(sys < 120 && dias < 80) {
            checkSafeLevels.setText("Normal Blood Pressure ");
        }
        else if(sys >= 120 && sys <= 129 && dias < 80){
            checkSafeLevels.setText("Elevated ");
        }
        else if(sys >= 130 && sys <= 139 || dias >= 80 && dias <= 89){
            checkSafeLevels.setText("Hypertension (Stage 1)");
        }
        else if(sys >= 140 && sys <= 179 || dias >= 90 && dias < 120){
            checkSafeLevels.setText("Hypertension (Stage 2)");
        }
        else {
            checkSafeLevels.setText("Hypertensive Crisis! Seek Emergency Care!");
        }
    }

    private void saveBloodPressureData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_BloodPressure, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SYSTOLIC_INPUT, systolicInput.getText().toString());
        editor.putString(DIASTOLIC_INPUT, diastolicInput.getText().toString());
        editor.putString(DATE_MEASURED, dateMeasured.getText().toString());

        editor.apply();
        Toast.makeText(getActivity().getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadBloodPressureData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_BloodPressure, Context.MODE_MULTI_PROCESS);
        loadSystolic = sharedPreferences.getString(SYSTOLIC_INPUT, "");
        loadDiastolic = sharedPreferences.getString(DIASTOLIC_INPUT, "");
        loadDate = sharedPreferences.getString(DATE_MEASURED, "");
    }

    public void updateBloodPressureData(){
        systolicInput.setText(loadSystolic);
        diastolicInput.setText(loadDiastolic);
        dateMeasured.setText(loadDate);
    }

}

