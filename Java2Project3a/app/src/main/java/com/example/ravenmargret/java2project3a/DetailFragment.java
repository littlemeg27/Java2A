/**
 * Created by Brenna Pavlinchak on 12/14/15.
 */

package com.example.ravenmargret.java2project3a;


import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DetailFragment extends Fragment implements View.OnClickListener
{
    public static final String KEY = "PersonKey";
    Button deletePersonButton;

    public DetailFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        deletePersonButton = (Button)getView().findViewById(R.id.deletePersonButton);
        deletePersonButton.setOnClickListener(this);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle args = getArguments();
        if (args != null)
        {
            String whereClause = Contract.ID + "=?";
            String[] whereArgs = new String[] { args.getInt(KEY) + "" };
            String[] columns = {Contract.ID, Contract.FIRST_NAME, Contract.LAST_NAME, Contract.JOB, Contract.AGE};
            Cursor cursor = getActivity().getContentResolver().query(Uri.parse(Contract.DATA_SOURCE_URI), columns, whereClause, whereArgs, null);
            String firstName = cursor.getString(cursor.getColumnIndex(Contract.FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(Contract.LAST_NAME));
            String job = cursor.getString(cursor.getColumnIndex(Contract.JOB));
            String age = cursor.getString(cursor.getColumnIndex(Contract.AGE));

            TextView firstNameText = (TextView) getActivity().findViewById(R.id.firstNameTextView);
            firstNameText.setText(firstName);

            TextView lastNameText = (TextView) getActivity().findViewById(R.id.lastNameTextView);
            lastNameText.setText(lastName);

            TextView jobText = (TextView) getActivity().findViewById(R.id.jobTextView);
            jobText.setText(job);

            TextView ageText = (TextView) getActivity().findViewById(R.id.ageTextView);
            ageText.setText(age);
        }
    }

//    public void updateText()
//    {
//        TextView firstNameText = (TextView) getActivity().findViewById(R.id.firstNameTextView);
//        firstNameText.setText(firstName);
//
//        TextView lastNameText = (TextView) getActivity().findViewById(R.id.lastNameTextView);
//        lastNameText.setText(object.getmLastName());
//
//        TextView jobText = (TextView) getActivity().findViewById(R.id.jobTextView);
//        jobText.setText(object.getmJob());
//
//        TextView ageText = (TextView) getActivity().findViewById(R.id.ageTextView);
//        ageText.setText(object.getmAge());
//    }

    @Override
    public void onClick(View v)
    {
        Toast.makeText(getActivity(), "Contact Deleted", Toast.LENGTH_LONG).show();

        //FormUtil.delete(getArguments().getInt(KEY), getActivity());

        getActivity().finish();
    }
}
