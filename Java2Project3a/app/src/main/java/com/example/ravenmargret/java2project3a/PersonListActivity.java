/**
 * Created by Brenna Pavlinchak on 12/14/15.
 */
package com.example.ravenmargret.java2project3a;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class PersonListActivity extends Activity implements OnClickListener, PersonListFragment.OnFragmentInteractionListener
{

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        manager = getFragmentManager();

        PersonListFragment listFragment = new PersonListFragment();
        showListFragment(listFragment);

        findViewById(R.id.addPersonButton).setOnClickListener(this);
    }

    private void showListFragment(Fragment listFrag)
    {
        manager.beginTransaction().replace(R.id.listContainer, listFrag).commit();
    }

    @Override
    public void onClick(View v)
    {
        Intent nextActivity = new Intent(this, FormActivity.class);
        startActivityForResult(nextActivity, 40404040);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        PersonListFragment fragment = (PersonListFragment) getFragmentManager().findFragmentById(R.id.listContainer);
    }

    public void onFragmentInteraction(int cursorID)
    {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Contract.ID, cursorID);
        Log.i("list", "onFragmentInteraction: " + cursorID);
        startActivityForResult(intent, 80808080);
        Log.i("list", "onFragmentInteraction: " + cursorID);
    }
}

