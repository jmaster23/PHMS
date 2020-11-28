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

public class HeartRate extends Fragment {
    public static final String PREFS_HeartRate = "sharedHeartRatePrefs";
    public static final String HEART_INPUT = "heartRate";


    private EditText heartInput;
    private Button heartRateSubmit, viewSavedHeartRate;

    private String loadHeart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_heart_rate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //heartRateOutput = (TextView) getActivity().findViewById(R.id.heartRateOutput);
        heartInput = (EditText) getActivity().findViewById(R.id.heartRateInput);
        heartRateSubmit = (Button) getActivity().findViewById(R.id.heartRateSubmit);
        viewSavedHeartRate = (Button) getActivity().findViewById(R.id.viewSavedHeartRate);

        heartRateSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartInput.setText(heartInput.getText().toString());
                saveHeartData();
            }
        });

        viewSavedHeartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent heart_rate_intent = new Intent(getActivity().getApplicationContext(), SavedData.class);
                startActivity(heart_rate_intent);
            }
        });
       loadHeartData();
        updateHeartData();
    }

    private void saveHeartData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_HeartRate, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(HEART_INPUT, heartInput.getText().toString());

        editor.apply();
        Toast.makeText(getActivity().getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadHeartData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_HeartRate, Context.MODE_MULTI_PROCESS);
        loadHeart = sharedPreferences.getString(HEART_INPUT, "");
    }

    public void updateHeartData(){
        heartInput.setText(loadHeart);
    }
}

