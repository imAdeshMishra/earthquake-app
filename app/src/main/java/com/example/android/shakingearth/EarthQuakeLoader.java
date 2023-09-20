package com.example.android.shakingearth;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class EarthQuakeLoader extends AsyncTaskLoader<List<EarthquakeData>> {

    private String mUrl;

    public EarthQuakeLoader(Context context, String url){
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<EarthquakeData> loadInBackground() {
        if(mUrl==null){
            return null;
        }

        List<EarthquakeData> earthquakes = QueryUtils.extractEarthquakes(mUrl);

        return earthquakes;
    }
}
