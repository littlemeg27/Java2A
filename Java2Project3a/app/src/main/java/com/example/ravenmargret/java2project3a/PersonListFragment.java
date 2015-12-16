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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class PersonListFragment extends ListFragment
{
    //private OnFragmentInteractionListener mListener;
    ArrayList<String> dataForm = new ArrayList<String>();

    public PersonListFragment()
    {

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            //mListener = (OnFragmentInteractionListener) activity;
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
        super.onActivityCreated(savedInstanceState);

        String[] columns = {Contract.ID, Contract.FIRST_NAME, Contract.LAST_NAME, Contract.JOB, Contract.AGE};

        Cursor cursor = getActivity().getContentResolver().query(Uri.parse(Contract.DATA_SOURCE_URI), columns, null, null, null);

        String[] fromDatabase = {Contract.FIRST_NAME};
        int[] setToList = {android.R.id.text1};
        CursorAdapter adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor, fromDatabase, setToList, 0);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        String selectedItem = dataForm.get(position).toString();
        Log.e("Selected", selectedItem);

        Intent intent = new Intent(getActivity(), PersonListActivity.class);
        intent.putStringArrayListExtra("dataForm", dataForm);
        startActivityForResult(intent, 45454545);


//        Bundle extras = new Bundle();
//        extras.putString(DetailActivity.PERSONKEY, );
//
//        intent.putExtras(extras);
//        startActivityForResult(intent, 45454545);
    }

}
