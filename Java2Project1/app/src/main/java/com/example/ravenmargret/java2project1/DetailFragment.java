/**
 * Created by Brenna Pavlinchak on 10/10/15.
 */

package com.example.ravenmargret.java2project1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment
{
    public static final String WEATHERKEY = "theWeatherKey";

    public DetailFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container2, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle args = getArguments();
        if (args != null)
        {
            // Set article based on argument passed in
            updateText((Weather)args.getSerializable(WEATHERKEY));
        }
    }

    public void updateText(Weather weatherObject)
    {
        TextView dayText = (TextView) getActivity().findViewById(R.id.dayTextView);
        dayText.setText(weatherObject.getmDay());

        TextView forecastTextView = (TextView) getActivity().findViewById(R.id.forecastTextView);
        forecastTextView.setText(weatherObject.getmForcast());

        TextView metricForecastTextView = (TextView) getActivity().findViewById(R.id.metricForecastTextView);
        metricForecastTextView.setText(weatherObject.getmForcastMetric());
    }
}
