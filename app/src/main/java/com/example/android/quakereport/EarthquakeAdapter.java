package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MUJ on 25-Mar-18.
 */

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeData>
{
    public EarthquakeAdapter(Context context, ArrayList<EarthquakeData> objects)
    {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        EarthquakeData earthquakeData = getItem(position);

        //Formating magnitude to show value upto one decimal place
        String formattedMagnitude = formatMagnitude(earthquakeData.getMagnitude());
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.tv_magnitude);
        magnitudeTextView.setText(formattedMagnitude);

        //Setting proper background color on thr magnitude circle
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(earthquakeData.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);



        //Spliting the location into offset and primary location
        String Location = earthquakeData.getLocation();
        String locationOffset, primaryLocation;
        if (Location.indexOf(",") == -1)
        {
            locationOffset = "Near the";
            primaryLocation = Location;
        }
        else
        {
            locationOffset = Location.substring(0, Location.indexOf(","));
            primaryLocation = Location.substring(Location.indexOf(",")+2);
        }

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.tv_location);
        locationTextView.setText(primaryLocation);

        TextView offsetTextView = listItemView.findViewById(R.id.tv_location_offset);
        offsetTextView.setText(locationOffset);

        Date dateObject = new Date(earthquakeData.getDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        String formattedDate = dateFormat.format(dateObject);

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.tv_date);
        dateTextView.setText(formattedDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String formattedTime = timeFormat.format(dateObject);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.tv_time);
        timeTextView.setText(formattedTime);

        return listItemView;
    }

    private int getMagnitudeColor(Double magnitude)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor)
        {
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
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

    private String formatMagnitude(Double magnitude)
    {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
