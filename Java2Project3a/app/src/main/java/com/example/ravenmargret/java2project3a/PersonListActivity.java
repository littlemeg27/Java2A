/**
 * Created by Brenna Pavlinchak on 12/14/15.
 */
package com.example.ravenmargret.java2project3a;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PersonListActivity extends Activity implements View.OnClickListener
{
    FragmentManager manager;
    public static final String KEY = "theKey";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        manager = getFragmentManager();

        PersonListFragment listFragment = new PersonListFragment();
        showListFragment(listFragment);

        findViewById(R.id.addPersonButton).setOnClickListener(this);

        Intent callingIntent = getIntent();
        ArrayList dataList = callingIntent.getStringArrayListExtra(KEY);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putStringArrayListExtra("dataList", dataList);
        startActivityForResult(intent, 45454545);
    }

    private void showListFragment(Fragment listFrag)
    {
        manager.beginTransaction().replace(R.id.container, listFrag).commit();
    }

    @Override
    public void onClick(View v)
    {
        Intent nextActivity = new Intent(this, FormActivity.class);
        startActivityForResult(nextActivity, 27272727);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Refresh list here
        //Find person list frag call load data to update list
        PersonListFragment fragment = (PersonListFragment) getFragmentManager().findFragmentById(R.id.container);
    }


//    @Override
//    public void onFragmentInteraction(Form formObject)
//    {
//        Bundle extras = new Bundle();
//        extras.putSerializable(DetailActivity.PERSONKEY, formObject);
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtras(extras);
//        startActivityForResult(intent, 45454545);
//    }
}

