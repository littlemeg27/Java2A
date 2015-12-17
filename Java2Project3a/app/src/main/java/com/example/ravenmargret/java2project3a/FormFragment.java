/**
 * Created by Brenna Pavlinchak on 12/14/15.
 */
package com.example.ravenmargret.java2project3a;

import android.app.Fragment;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FormFragment extends Fragment implements View.OnClickListener
{
    Button saveButton;
    EditText firstNameText;
    EditText lastNameText;
    EditText jobText;
    EditText ageText;

    public FormFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    private void addCrud(String firstName, String lastName, String job, int age)
    {
        ContentValues values = new ContentValues();
        values.put(Contract.FIRST_NAME, firstName);
        values.put(Contract.LAST_NAME, lastName);
        values.put(Contract.JOB, job);
        values.put(Contract.AGE, age);
        Uri crudUri = getActivity().getContentResolver().insert(Uri.parse(Contract.DATA_SOURCE_URI), values);
        Log.e("Main", "TESTING" + crudUri.getLastPathSegment());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        firstNameText = (EditText)getView().findViewById(R.id.firstNameText);
        lastNameText = (EditText)getView().findViewById(R.id.lastNameText);
        jobText = (EditText)getView().findViewById(R.id.jobText);
        ageText = (EditText)getView().findViewById(R.id.ageText);

        saveButton = (Button)getView().findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String job = jobText.getText().toString();
        String age = ageText.getText().toString();

        Toast.makeText(getActivity(), "Contact Added", Toast.LENGTH_LONG).show();

        addCrud(firstName, lastName, job, Integer.parseInt(age));

        getActivity().finish();
    }
}
