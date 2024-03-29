/**
 * Created by Brenna Pavlinchak on 11/27/15.
 */

package com.example.ravenmargret.java2project1;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SongTask extends AsyncTask<String, Void, ArrayList<Song>>
{
    final String TAG = "API DEMO AsyncTask";
    ArrayList<Song> concertList = new ArrayList<Song>();
    Context mContext;
    ProgressDialog dialog;
    SongDataReceiver mReceiver;

    public SongTask(Context mContext, SongDataReceiver _receiver)
    {
        this.mContext = mContext;
        dialog = new ProgressDialog(mContext);
        mReceiver = _receiver;
    }

    public interface SongDataReceiver
    {
        void receiveData(ArrayList<Song> concertList);
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.setTitle("Network Call");
        dialog.show();
    }

    @Override
    protected ArrayList<Song> doInBackground(String... params)
    {
        String result = "";

        try
        {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream is = connection.getInputStream();

            result = IOUtils.toString(is);
            is.close();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            JSONObject song = new JSONObject(result);
            JSONObject resultsPageObject = song.getJSONObject("resultsPage");
            JSONObject resultsObject = resultsPageObject.getJSONObject("results");

            JSONArray eventArray = resultsObject.getJSONArray("event");

            for (int i = 0; i < eventArray.length(); i++)
            {
                JSONObject insideObject = eventArray.getJSONObject(i);
                String type;
                String eventName;
                String uri;

                type = insideObject.getString("type");
                eventName = insideObject.getString("displayName");
                uri = insideObject.getString("uri");

                JSONObject startObject = insideObject.getJSONObject("start");
                String date; //Inside of Start object
                date = startObject.getString("date");

                JSONObject locationObject = insideObject.getJSONObject("location");
                String location; //Inside of location object
                location = locationObject.getString("city");

                JSONObject venueObject = insideObject.getJSONObject("venue");
                String venue; //Inside of venue object
                venue = venueObject.getString("displayName");

                concertList.add(new Song(type, venue, uri, date, location, eventName));
            }
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        try
        {
            ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo network = manager.getActiveNetworkInfo();

            String testing = "Test";

            if(network !=null && network.isConnected())
            {
                SongUtil saveData = new SongUtil();
                saveData.save(concertList, mContext);
                Log.e("Saving Data ", testing);
            }
            else
            {
                SongUtil loadData = new SongUtil();
                concertList = loadData.load(mContext);
                Log.e("Loading Data ", testing);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return concertList;
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<Song> songAPI)
    {
        super.onPostExecute(songAPI);

        dialog.cancel();
        mReceiver.receiveData(concertList);
    }
}
