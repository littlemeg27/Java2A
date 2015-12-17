/**
 * Created by Brenna Pavlinchak on 12/14/15.
 */

package com.example.ravenmargret.java2project3a;


import android.app.Fragment;
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
//            ArrayList<Form> forms = FormUtil.load(getActivity());
//            Form form = forms.get(args.getInt(KEY));
//            updateText(form);
        }
    }

//    public void updateText(Form object)
//    {
//        TextView firstNameText = (TextView) getActivity().findViewById(R.id.firstNameTextView);
//        firstNameText.setText(object.getmFirstName());
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
