package com.example.livephms;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.loader.content.AsyncTaskLoader;
import android.support.v4.content.AsyncTaskLoader;

public class MoniteringSystemAndAlerts extends AsyncTaskLoader<String> {
    private String mQueryString;

    public MoniteringSystemAndAlerts(@NonNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        super.onStartLoading();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}
