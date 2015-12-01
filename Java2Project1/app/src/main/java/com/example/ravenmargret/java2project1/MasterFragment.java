/**
 * Created by Brenna Pavlinchak on 11/27/15.
 */

package com.example.ravenmargret.java2project1;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class MasterFragment extends ListFragment implements SongTask.SongDataReceiver
{
    ArrayList<Song> mObjects;
    private OnFragmentInteractionListener mListener;
    public static String fileName = "api.ser";

    @Override
    public void receiveData(ArrayList<Song> songList)
    {
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, songList));
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
        super.onActivityCreated(savedInstanceState);

        try
        {
            SongTask myTask = new SongTask(getActivity(), this);
            myTask.execute("http://api.songkick.com/api/3.0/metro_areas/13579/calendar.json?apikey=lQbaqybx2HCPaqV0");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        if (null != mListener)
        {
            Song s = (Song)getListAdapter().getItem(position);
            mListener.onFragmentInteraction(s);
        }
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Song songObject);
    }
}
