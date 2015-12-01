/**
 * Created by Brenna Pavlinchak on 11/27/15.
 */

package com.example.ravenmargret.java2project1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MasterActivity extends ActionBarActivity implements MasterFragment.OnFragmentInteractionListener
{
    FragmentManager manager;
    final String TAG = "API TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        manager = getFragmentManager();

        MasterFragment masterFragment = new MasterFragment();
        showMasterFragment(masterFragment);
    }

    private void showMasterFragment(Fragment masterFrag)
    {
        manager.beginTransaction().add(R.id.container, masterFrag).commit();
    }

    private void showDetailFragment(Fragment detailFrag)
    {
        manager.beginTransaction().replace(R.id.container2, detailFrag).commit();
    }

    @Override
    public void onFragmentInteraction(Song songObject)
    {
        DetailFragment detailFragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(DetailFragment.SONGKEY, songObject);
        detailFragment.setArguments(args);

        showDetailFragment(detailFragment);
    }
}
