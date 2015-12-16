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
              // Set article based on argument passed in
              //updateText((ArrayList)args.getStringArrayList(KEY).toString());
              //String selectedItem = dataForm.get(position).toString();
          }
      }

      public void updateText()
      {
        TextView firstNameTextView = (TextView) getActivity().findViewById(R.id.firstTextView);
          //firstNameTextView.setText();

        TextView lastNameTextView = (TextView) getActivity().findViewById(R.id.lastTextView);
          //lastNameTextView.setText();

        TextView ageTextView = (TextView) getActivity().findViewById(R.id.ageTextView);
          //ageTextView.setText();
    }

    @Override
    public void onClick(View v)
    {
        Toast.makeText(getActivity(), "Contact Deleted", Toast.LENGTH_LONG).show();

        getActivity().finish();
    }
}
