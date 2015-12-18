/**
 * Created by Brenna Pavlinchak on 12/14/15.
 */

package com.example.ravenmargret.java2project3a;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class DetailActivity extends Activity
{
    FragmentManager manager;
    public static final String LISTKEY = "listKey";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        manager = getFragmentManager();

        DetailFragment detailFragment = new DetailFragment();

        Intent callingIntent = getIntent();
        int object = callingIntent.getIntExtra(LISTKEY, 0);
        setResult(RESULT_OK, callingIntent);

        Bundle args = new Bundle();
        args.putInt(DetailFragment.KEY, object);
        detailFragment.setArguments(args);

        showDetailFragment(detailFragment);
    }

    private void showDetailFragment(Fragment detailFrag)
    {
        manager.beginTransaction().replace(R.id.detailContainer, detailFrag).commit();
    }
}
