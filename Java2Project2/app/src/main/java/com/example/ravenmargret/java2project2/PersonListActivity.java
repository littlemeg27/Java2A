/**
 * Created by Brenna Pavlinchak on 12/6/15.
 */

package com.example.ravenmargret.java2project2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PersonListActivity extends Activity implements View.OnClickListener, PersonListFragment.OnFragmentInteractionListener
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
        manager.beginTransaction().replace(R.id.container, listFrag).commit();
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
        PersonListFragment fragment = (PersonListFragment) getFragmentManager().findFragmentById(R.id.container);
        fragment.loadData();
    }

    @Override
    public void onFragmentInteraction(Form formObject)
    {
        Bundle extras = new Bundle();
        extras.putSerializable(DetailActivity.LISTKEY, formObject);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(extras);
        startActivityForResult(intent, 80808080);
    }
}
