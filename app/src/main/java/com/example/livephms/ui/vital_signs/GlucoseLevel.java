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

public class GlucoseLevel extends Fragment {
    public static final String PREFS_Glucose = "sharedGlucosePrefs";
    public static final String GLUCOSE_INPUT = "glucose";


    private EditText glucoseInput;
    private TextView glucoseSafeLevels;
    private Button glucoseSubmit, viewSavedGlucoseLevel;

    private String loadGlucose;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_glucose_level, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //final TextView glucoseOutput = (TextView) getActivity().findViewById(R.id.glucoseOutput);
        glucoseInput = (EditText) getActivity().findViewById(R.id.glucoseInput);
        glucoseSafeLevels = (TextView) getActivity().findViewById(R.id.glucoseSafeLevels);
        glucoseSubmit = (Button) getActivity().findViewById(R.id.glucoseSubmit);
        viewSavedGlucoseLevel = (Button) getActivity().findViewById(R.id.viewSavedGlucoseLevel);

        glucoseSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glucoseInput.setText(glucoseInput.getText().toString());
                glucoseSafeLevels();
                saveGlucoseData();
            }
        });

        viewSavedGlucoseLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent glucose_level_intent = new Intent(getActivity().getApplicationContext(), com.example.livephms.ui.vital_signs.SavedData.class);
                startActivity(glucose_level_intent);
            }
        });
        loadGlucoseData();
        updateGlucoseData();

    }

    private void glucoseSafeLevels(){
        int glucose = (Integer.valueOf(glucoseInput.getText().toString()));

        if(glucose <= 200) {
            glucoseSafeLevels.setText("Normal Glucose ");
        }
        else if(glucose >= 190 && glucose <= 230){
            glucoseSafeLevels.setText("Impaired Glucose");
        }
        else {
            glucoseSafeLevels.setText("Diabetic ");
            MoniteringSystemAndAlerts MSA = new MoniteringSystemAndAlerts();
            MSA.glucoseAlert();
        }
    }

    private void saveGlucoseData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_Glucose, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(GLUCOSE_INPUT, glucoseInput.getText().toString());

        editor.apply();
        Toast.makeText(getActivity().getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadGlucoseData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_Glucose, Context.MODE_MULTI_PROCESS);
        loadGlucose = sharedPreferences.getString(GLUCOSE_INPUT, "");
    }

    public void updateGlucoseData(){
        glucoseInput.setText(loadGlucose);
    }

}

