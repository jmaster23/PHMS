package com.example.livephms.ui.vital_signs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.livephms.R;
import com.example.livephms.ui.vital_signs.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class vitalsignsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vitalsigns_main);
        com.example.livephms.ui.vital_signs.SectionsPagerAdapter sectionsPagerAdapter = new com.example.livephms.ui.vital_signs.SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}