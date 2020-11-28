package com.example.livephms.ui.vital_signs;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.livephms.ui.vital_signs.HeartRate;
import com.example.livephms.R;
import com.example.livephms.ui.vital_signs.Temperature;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                BloodPressure bloodPressure = new BloodPressure();
                return bloodPressure;
            case 1:
                Cholesterol cholesterol = new Cholesterol();
                return cholesterol;
            case 2:
               GlucoseLevel glucoseLevel = new GlucoseLevel();
               return glucoseLevel;
            case 3:
               com.example.livephms.ui.vital_signs.HeartRate heartRate = new com.example.livephms.ui.vital_signs.HeartRate();
               return heartRate;
            case 4:
               com.example.livephms.ui.vital_signs.Temperature temperature = new com.example.livephms.ui.vital_signs.Temperature();
               return temperature;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 5 total pages.
        return 5;
    }
}