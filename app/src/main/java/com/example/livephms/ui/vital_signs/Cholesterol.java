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

import com.example.livephms.MoniteringSystemAndAlerts;
import com.example.livephms.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Cholesterol extends Fragment {
    public static final String PREFS_Cholesterol = "sharedCholesterolPrefs";
    public static final String CHOLESTEROL_INPUT = "cholesterol";
    public static final String HDL_INPUT = "hdl";
    public static final String LDL_INPUT = "ldl";
    public static final String TRIGLYCERIDES_INPUT = "triglycerides";

    private EditText cholesterolInput;
    private EditText hdlInput;
    private EditText ldlInput;
    private EditText triglyceridesInput;
    private TextView cholesterolSafeLevels, HDLSafeLevels, LDLSafeLevels;
    private Button cholesterolSubmit, viewSavedCholesterol;

    private String loadCholesterol, loadHdl, loadLdl, loadTriglycerides;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cholesterol, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //cholesterolOutput = (TextView) getActivity().findViewById(R.id.cholesterolOutput);
        cholesterolInput = (EditText) getActivity().findViewById(R.id.totalCholesterolInput);
        hdlInput = (EditText) getActivity().findViewById(R.id.hdlCholesterolInput);
        ldlInput = (EditText) getActivity().findViewById(R.id.ldlCholesterolInput);
        triglyceridesInput = (EditText) getActivity().findViewById(R.id.triglyceridesInput);
        cholesterolSafeLevels = (TextView) getActivity().findViewById(R.id.cholesterolSafeLevels);
        HDLSafeLevels = (TextView) getActivity().findViewById(R.id.HDLSafeLevels);
        LDLSafeLevels = (TextView) getActivity().findViewById(R.id.LDLSafeLevels);
        cholesterolSubmit = (Button) getActivity().findViewById(R.id.cholesterolSubmit);
        viewSavedCholesterol = (Button) getActivity().findViewById(R.id.viewSavedCholesterol);

        cholesterolSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cholesterolInput.setText(cholesterolInput.getText().toString());
                hdlInput.setText(hdlInput.getText().toString());
                ldlInput.setText(ldlInput.getText().toString());
                triglyceridesInput.setText(triglyceridesInput.getText().toString());
                cholesterolSafeLevels();
                HDLSafeLevels();
                LDLSafeLevels();
                saveCholesterolData();
            }
        });

        viewSavedCholesterol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cholesterol_intent = new Intent(getActivity().getApplicationContext(), com.example.livephms.ui.vital_signs.SavedData.class);
                startActivity(cholesterol_intent);
            }
        });
        loadCholesterolData();
        updateCholesterolData();

    }

    private void cholesterolSafeLevels(){
        int chol = (Integer.valueOf(cholesterolInput.getText().toString()));

        if(chol < 200) {
            cholesterolSafeLevels.setText("Normal Total Cholesterol");
        }
        else if(chol >= 200 && chol <= 239){
            cholesterolSafeLevels.setText("Borderline High Total Cholesterol");
        }
        else {
            cholesterolSafeLevels.setText("High Total Cholesterol");
            MoniteringSystemAndAlerts MSA = new MoniteringSystemAndAlerts();
            MSA.cholesterolAlert();
        }

    }

    private void HDLSafeLevels(){
        int hdl = (Integer.valueOf(hdlInput.getText().toString()));

        if(hdl < 40) {
            HDLSafeLevels.setText("High Risk For Heart Disease ");
        }
        else if(hdl >= 40 && hdl <= 59){
            HDLSafeLevels.setText("Good HDL Levels");
        }
        else {
            HDLSafeLevels.setText("Preventative Against Heart Disease ");
        }
    }

    private void LDLSafeLevels(){
        int ldl = (Integer.valueOf(ldlInput.getText().toString()));

        if(ldl < 100) {
            LDLSafeLevels.setText("Normal LDL Levels");
        }
        else if(ldl >= 100 && ldl <= 129){
            LDLSafeLevels.setText("Slightly Above Normal LDL");
        }
        else if(ldl >= 130 && ldl <= 159){
            LDLSafeLevels.setText("Borderline High LDL");
        }
        else if(ldl >= 160 && ldl <= 189){
            LDLSafeLevels.setText("High LDL");
        }
        else {
            LDLSafeLevels.setText("Very High LDL");
        }
    }

    private void saveCholesterolData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_Cholesterol, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(CHOLESTEROL_INPUT, cholesterolInput.getText().toString());
        editor.putString(HDL_INPUT, hdlInput.getText().toString());
        editor.putString(LDL_INPUT, ldlInput.getText().toString());
        editor.putString(TRIGLYCERIDES_INPUT, triglyceridesInput.getText().toString());

        editor.apply();
        Toast.makeText(getActivity().getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadCholesterolData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_Cholesterol, Context.MODE_MULTI_PROCESS);
        loadCholesterol = sharedPreferences.getString(CHOLESTEROL_INPUT, "");
        loadHdl = sharedPreferences.getString(HDL_INPUT, "");
        loadLdl = sharedPreferences.getString(LDL_INPUT, "");
        loadTriglycerides = sharedPreferences.getString(TRIGLYCERIDES_INPUT, "");
    }

    public void updateCholesterolData(){
        cholesterolInput.setText(loadCholesterol);
        hdlInput.setText(loadHdl);
        ldlInput.setText(loadLdl);
        triglyceridesInput.setText(loadTriglycerides);
    }

}
