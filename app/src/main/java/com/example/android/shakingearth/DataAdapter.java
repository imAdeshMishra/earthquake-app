package com.example.android.shakingearth;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataAdapter extends ArrayAdapter<EarthquakeData> {
    private static final String LOCATION_SEPARATOR = " of ";

    DataAdapter(Activity content, ArrayList<EarthquakeData> earthquakes) {
        super(content, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_data, parent, false);
        }

        EarthquakeData currentData = getItem(position);


        double magnitude = currentData.getMagnitude();

        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");

        String formattedMaggnitude = magnitudeFormat.format(magnitude);
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(formattedMaggnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentData.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


//        Location Breakdown
        String place = currentData.getCity();

        String primaryLocation;
        String locationOffset;

        if (place.contains(LOCATION_SEPARATOR)) {
            String[] splitted_location = place.split(LOCATION_SEPARATOR);
//            TextView locationOffset = (TextView) listItemView.findViewById(R.id.location_offset);
//            locationOffset.setText(splitted_location[0] + LOCATION_SEPARATOR);

//            TextView primaryLocation = (TextView) listItemView.findViewById(R.id.primary_location);
//            primaryLocation.setText(splitted_location[1]);


            locationOffset = splitted_location[0] + LOCATION_SEPARATOR;
            // Primary location should be "Cairo, Egypt"
            primaryLocation = splitted_location[1];
        } else {
//            TextView locationOffset = (TextView) listItemView.findViewById(R.id.location_offset);
//            locationOffset.setText("Near by");
//
//            TextView primaryLocation = (TextView) listItemView.findViewById(R.id.primary_location);
//            primaryLocation.setText(place);

            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = place;
        }

        // Find the TextView with view ID location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        // Display the location of the current earthquake in that TextView
        primaryLocationView.setText(primaryLocation);

        // Find the TextView with view ID location offset
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Display the location offset of the current earthquake in that TextView
        locationOffsetView.setText(locationOffset);


        Date dateObject = new Date(currentData.getTime());
//        For Formatting date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd,yyyy");
        String formattedDate = dateFormat.format(dateObject);
        dateView.setText(formattedDate);
        //        For Formatting time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String formattedTime = timeFormat.format(dateObject);
        timeView.setText(formattedTime);


//        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
//        String formattedDate = formatDate(dateObject);
//        dateView.setText(formattedDate);

//        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
//        String formattedTime = formatTime(dateObject);
//        timeView.setText(formattedTime);

        return listItemView;
    }

//    private String formatDate(Date dateObject){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd,yyyy");
//                return dateFormat.format(dateObject);
//    }
//
//    private String formatTime(Date dateObject) {
//        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
//        return timeFormat.format(dateObject);
//    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
