package com.example.ravenmargret.java2project1;
/**
 * Created by Brenna Pavlinchak on 11/27/15.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

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
            ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo network = manager.getActiveNetworkInfo();
            if(network !=null && network.isConnected())
            {
                SongUtil saveData = new SongUtil();
                saveData.save(concertList, mContext);
            }
            else
            {
                SongUtil loadData = new SongUtil();
                loadData.load(mContext);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            JSONObject song = new JSONObject(result);
            JSONObject forecastObject = song.getJSONObject("forecast");
            JSONObject weatherObject = forecastObject.getJSONObject("txt_forecast");

            JSONArray weatherArray = weatherObject.getJSONArray("forecastday");

            for (int i = 0; i < weatherArray.length(); i++)
            {
                JSONObject insideObject = weatherArray.getJSONObject(i);
                String type;
                String artist;
                String uri;
                String date; //Inside of Start object
                String location; //Inside of location object

                type = insideObject.getString("type");
                artist = insideObject.getString("displayName");
                uri = insideObject.getString("uri");

                concertList.add(new Song(type, artist, uri, date, location));
            }

        }
        catch (JSONException e)
        {

        }
        return concertList;
    }



    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(ArrayList<Song> weatherAPI)
    {
        super.onPostExecute(weatherAPI);

        dialog.cancel();
        mReceiver.receiveData(concertList);
    }
}
