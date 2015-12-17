/**
 * Created by Brenna Pavlinchak on 12/14/15.
 */

package com.example.ravenmargret.java2project3a;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class PersonListFragment extends ListFragment
{
    private OnFragmentInteractionListener mListener;

    public PersonListFragment()
    {

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mListener = (OnFragmentInteractionListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        String testing = "";
        super.onActivityCreated(savedInstanceState);

        String[] columns = {Contract.ID, Contract.FIRST_NAME, Contract.LAST_NAME, Contract.JOB, Contract.AGE};

        Cursor cursor = getActivity().getContentResolver().query(Uri.parse(Contract.DATA_SOURCE_URI), columns, null, null, null);

        String[] fromDatabase = {Contract.FIRST_NAME};
        int[] setToList = {android.R.id.text1};
        CursorAdapter adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor, fromDatabase, setToList, 0);
        setListAdapter(adapter);
//        Log.e(Contract.ID, "Test");
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        mListener.onFragmentInteraction(position);
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(int formObject);
    }
}
