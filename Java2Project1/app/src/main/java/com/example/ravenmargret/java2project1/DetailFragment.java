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

public class DetailFragment extends Fragment
{
    public static final String SONGKEY = "songKey";

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
            updateText((Song)args.getSerializable(SONGKEY));
        }
    }

    public void updateText(Song songObject)
    {
        TextView typeText = (TextView) getActivity().findViewById(R.id.typeTextView);
        typeText.setText(songObject.getmType());

        TextView venueTextView = (TextView) getActivity().findViewById(R.id.venueTextView);
        venueTextView.setText(songObject.getmVenue());

        TextView uriTextView = (TextView) getActivity().findViewById(R.id.uriTextView);
        uriTextView.setText(songObject.getmURI());

        TextView dateTextView = (TextView) getActivity().findViewById(R.id.dateTextView);
        dateTextView.setText(songObject.getmDate());

        TextView locationTextView = (TextView) getActivity().findViewById(R.id.locationTextView);
        locationTextView.setText(songObject.getmLocation());

        TextView eventNameTextView = (TextView) getActivity().findViewById(R.id.eventNameTextView);
        eventNameTextView.setText(songObject.getmEventName());
    }
}
