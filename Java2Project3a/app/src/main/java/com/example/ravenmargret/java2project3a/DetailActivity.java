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
    public static final String FORMKEY = "theFormKey";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        manager = getFragmentManager();

        DetailFragment detailFragment = new DetailFragment();
        showDetailFragment(detailFragment);

        Intent callingIntent = getIntent();
        ArrayList dataList = callingIntent.getStringArrayListExtra(FORMKEY);

        Bundle args = new Bundle();
        args.putStringArrayList(DetailFragment.KEY, dataList);
        detailFragment.setArguments(args);

    }

    private void showDetailFragment(Fragment detailFrag)
    {
        manager.beginTransaction().replace(R.id.container1, detailFrag).commit();
    }

    public void sendText() //Trying to do the send out, not going so well
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }
}
