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
        Form object = (Form)callingIntent.getSerializableExtra(LISTKEY);
        setResult(RESULT_OK, callingIntent);

        Bundle args = new Bundle();
        args.putSerializable(DetailFragment.KEY, object);
        detailFragment.setArguments(args);
    }

    private void showDetailFragment(Fragment detailFrag)
    {
        manager.beginTransaction().replace(R.id.container, detailFrag).commit();
    }

    public void sendText(View v)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra("",);
        intent.putExtra("",);
        intent.putExtra("",);
        intent.putExtra("",);
        intent.setType("text/plain");

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }
}
