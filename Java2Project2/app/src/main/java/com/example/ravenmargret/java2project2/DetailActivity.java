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
        showDetailFragment(detailFragment);

        Intent callingIntent = getIntent();
        int object = callingIntent.getIntExtra(LISTKEY, 0);
        setResult(RESULT_OK, callingIntent);

        Bundle args = new Bundle();
        args.putInt(DetailFragment.KEY, object);
        detailFragment.setArguments(args);
    }

    private void showDetailFragment(Fragment detailFrag)
    {
        manager.beginTransaction().replace(R.id.container, detailFrag).commit();
    }

//    public void onClick(View v)
//    {
//        Intent sendText = new Intent();
//        sendText.setAction(Intent.ACTION_SEND);
//        sendText.putExtra(sendText.EXTRA_TEXT, );
//        sendText.putExtra("",);
//        sendText.putExtra("",);
//        sendText.putExtra("",);
//        sendText.setType("text/plain");
//
//        if (sendText.resolveActivity(getPackageManager()) != null)
//        {
//            startActivity(sendText);
//        }
//    }
}
