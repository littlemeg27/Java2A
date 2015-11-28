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
    ArrayList<Song> weatherForecast = new ArrayList<Song>();
    Context mContext;
    ProgressDialog dialog;
    WeatherDataReceiver mReceiver;

    public SongTask(Context mContext, WeatherDataReceiver _receiver)
    {
        this.mContext = mContext;
        dialog = new ProgressDialog(mContext);
        mReceiver = _receiver;
    }

    public interface WeatherDataReceiver
    {
        void receiveData(ArrayList<Song> weatherForecast);
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
    protected ArrayList<Weather> doInBackground(String... params)
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
            //Log.e("Testing",result);
            //Saving loading goes here

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
            ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE); //Check network class
            NetworkInfo network = manager.getActiveNetworkInfo();
            if(network !=null && network.isConnected())
            {
                WeatherUtil saveData = new WeatherUtil();
                saveData.save(weatherForecast, mContext);
            }
            else
            {
                //Move connect to Task
                WeatherUtil loadData = new WeatherUtil();
                loadData.load(mContext);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            JSONObject weather = new JSONObject(result);
            JSONObject forecastObject = weather.getJSONObject("forecast");
            JSONObject weatherObject = forecastObject.getJSONObject("txt_forecast");

            JSONArray weatherArray = weatherObject.getJSONArray("forecastday");

            for (int i = 0; i < weatherArray.length(); i++)
            {
                JSONObject insideObject = weatherArray.getJSONObject(i);
                String day;
                String forecast;
                String forecastMetric;

                day = insideObject.getString("title");
                forecast = insideObject.getString("fcttext");
                forecastMetric = insideObject.getString("fcttext_metric");
                Log.e("Weather data", forecast);

                weatherForecast.add(new Weather(day, forecast, forecastMetric));
            }

        }
        catch (JSONException e)
        {
            //Toast toast = Toast.makeText(MainActivity.this, "Something Happened", Toast.LENGTH_SHORT);
            //toast.show();
        }
        return weatherForecast;
    }



    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(ArrayList<Weather> weatherAPI)
    {
        super.onPostExecute(weatherAPI);

        dialog.cancel();
        mReceiver.receiveData(weatherForecast);
    }
}
