package serhanmd.basicweatherapp;

//Imports
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import serhanmd.basicweatherapp.WeatherData.Data;


public class MainPage extends AppCompatActivity {

    //Variables
    public static final String TAG = "MoLog:";
    TextView changeTemp;
    ImageView changeIcon;
    TextView changeCity;
    Gson gson = new Gson();
    Data data;

    //Executes whatever when the activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Starts the selected layout as the first page when app opens
        setContentView(R.layout.activity_main_page);

        //Set these variables to the proper IDs of the views found on the app interface
        changeTemp = findViewById(R.id.tempValue);
        changeIcon = findViewById(R.id.icon);
        changeCity = findViewById(R.id.cityName);
        //changeTemp.setVisibility(View.INVISIBLE);
        //Picasso.get().load("http://openweathermap.org/img/w/10d.png").into(changeIcon);

        Log.i(TAG,"Process started!");

        new JSONFeedArrayTask().execute(); //This command allows the class to run and make URL requests without crashing

    }

    //Reads the URL and extracts the JSON file to a local variable, ONLY HARD CODED VALUE SO FAR
    public class JSONFeedArrayTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
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

                Log.i(TAG, "Process Complete!");

            } catch(IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            int tmp = (int) data.getList().get(0).getMain().getTemp(); //HOW TO ACCESS THE DATA!!!!!!!!!!!!
            changeTemp.setText(""+tmp + " " + (char) 0x00B0+"C");
            Toast.makeText(getApplicationContext(),"Weather Found", Toast.LENGTH_SHORT).show();
        }
    }

}
