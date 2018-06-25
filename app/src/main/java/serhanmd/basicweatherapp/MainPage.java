package serhanmd.basicweatherapp;

//Imports
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import serhanmd.basicweatherapp.WeatherData.Data;

public class MainPage extends AppCompatActivity {

    //Variables
    public static final String TAG = "MoLog:";
    TextView changeTemp;
    Data[] weather;

    //Executes whatever when the activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        changeTemp = findViewById(R.id.tempValue);
        //Executes getJson method, which can get data from any city depending on the name
        Log.i(TAG,"Process started!");
        new JSONFeedArrayTask().execute();

    }

    //Reads the URL and extracts the JSON file to a local variable
    public class JSONFeedArrayTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                Log.i(TAG,"Initializing Variables...");
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://api.openweathermap.org/data/2.5/forecast?q=Kuwait%20City&APPID=361e3863fb571305e306d4be3472954b")
                        .build();
                Log.i(TAG,"Connecting to website");
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                Log.d(TAG, result);
                Log.i(TAG,"Generating Gson...");
                Gson gson = new Gson();
                Log.i(TAG,"Packing data into arrays step 1");
                //Type collectionType = new TypeToken<Collection<Data>>() {}.getType();
                Log.i(TAG,"Packing data into arrays step 2");
               // Collection<Data> enums = gson.fromJson(result, collectionType);
                Data data = gson.fromJson(result, Data.class);
                Log.i(TAG,"Packing data into arrays step 3");
                Log.d(TAG, ""+data.getCnt());
                Log.i(TAG, "Process Complete!");

            } catch(IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
