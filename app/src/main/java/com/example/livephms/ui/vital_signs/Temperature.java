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
import android.widget.Toast;
import com.example.livephms.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Temperature extends Fragment {
    public static final String PREFS_Temperature = "sharedTemperaturePrefs";
    public static final String TEMPERATURE_INPUT = "temperature";


    private EditText temperatureInput;
    private Button temperatureSubmit, viewSavedTemperature;

    private String loadTemperature;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //temperatureOutput = (TextView) getActivity().findViewById(R.id.temperatureOutput);
        temperatureInput = (EditText) getActivity().findViewById(R.id.temperatureInput);
        temperatureSubmit = (Button) getActivity().findViewById(R.id.temperatureSubmit);
        viewSavedTemperature = (Button) getActivity().findViewById(R.id.viewSavedTemperature);

        temperatureSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperatureInput.setText(temperatureInput.getText().toString());
                saveTemperatureData();
            }
        });

        viewSavedTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temperature_intent = new Intent(getActivity().getApplicationContext(), SavedData.class);
                startActivity(temperature_intent);
            }
        });
        loadTemperatureData();
        updateTemperatureData();
    }

    private void saveTemperatureData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_Temperature, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEMPERATURE_INPUT, temperatureInput.getText().toString());

        editor.apply();
        Toast.makeText(getActivity().getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadTemperatureData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_Temperature, Context.MODE_MULTI_PROCESS);
        loadTemperature = sharedPreferences.getString(TEMPERATURE_INPUT, "");
    }

    public void updateTemperatureData(){
        temperatureInput.setText(loadTemperature);
    }
}