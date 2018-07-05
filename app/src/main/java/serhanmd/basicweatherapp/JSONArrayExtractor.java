package serhanmd.basicweatherapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import serhanmd.basicweatherapp.Fragments.CurrentDay;
import serhanmd.basicweatherapp.WeatherData.Data;

import static serhanmd.basicweatherapp.WeatherUtils.sendData;

//Reads the URL and extracts the JSON file to a local variable, ONLY HARD CODED VALUE SO FAR
public class JSONArrayExtractor extends AsyncTask<String, Void, String> {

    //Variables
    private final String TAG = "MoLog:";
    private Gson gson = new Gson();
    private Data data;
    private CurrentDay currDay = new CurrentDay();

    @Override
    protected String doInBackground(String... strings) {

        try {
            Log.i(TAG,"JSONArrayExtractor Started.");
            Log.i(TAG,"Initializing Variables...");
            OkHttpClient client = new OkHttpClient();
            //Uses OkHttp to request access to the URL to read the data from OpenWeatherAPI website
            Request request = new Request.Builder()
                    .url("http://api.openweathermap.org/data/2.5/forecast?q=Kuwait%20City&APPID=361e3863fb571305e306d4be3472954b")
                    .build();
            Log.i(TAG,"Connecting to website");
            //Accesses the website and saves the data
            Response response = client.newCall(request).execute();
            //Saves the JSON data file into a string
            String result = response.body().string();
            Log.d(TAG, result);
            Log.i(TAG,"Generating Gson...");
            Log.i(TAG,"Packing data into arrays step 1");
            //Type collectionType = new TypeToken<Collection<Data>>() {}.getType();
            Log.i(TAG,"Packing data into arrays step 2");
            // Collection<Data> enums = gson.fromJson(result, collectionType);

            //Turns the JSON String into a java object using GSON
            data = gson.fromJson(result, Data.class);
            Log.i(TAG,"Packing data into arrays step 3");

            Log.i(TAG, "Data Process Complete!");

        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        sendData(data);
    }
}
