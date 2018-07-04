package serhanmd.basicweatherapp;

//Imports
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.TabLayout;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import serhanmd.basicweatherapp.WeatherData.Data;


public class MainPage extends AppCompatActivity {

    //Variables
    public static final String TAG = "MoLog:";
    TextView changeTemp;
    TextView changeCity;
    TextView changeCondition;
    TextView changeDescription;
    TextView changeDate;
    TextView descriptionTxt;
    TextView dateTxt;
    ImageView changeIcon;
    TabLayout tabLayout;
    Button refresh;
    Gson gson = new Gson();
    Data data;

    //Executes whatever when the activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Starts the selected layout as the first page when app opens
        setContentView(R.layout.activity_main_page);

        //Set these variables to the proper IDs of the views found on the app interface
        changeTemp = findViewById(R.id.tempValue);
        changeCondition = findViewById(R.id.condText);
        changeIcon = findViewById(R.id.icon);
        changeCity = findViewById(R.id.cityName);
        changeDescription = findViewById(R.id.descValue);
        changeDate = findViewById(R.id.dateValue);
        descriptionTxt = findViewById(R.id.descText);
        dateTxt = findViewById(R.id.dateText);
        refresh = findViewById(R.id.refreshButton);
        setViewVisibility(false);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONFeedArrayTask().execute();
            }
        });

        Log.i(TAG,"Process started!");

        new JSONFeedArrayTask().execute(); //This command allows the class to run and make URL requests without crashing

    }

    //Sets all the views to visible for true and invisible for false
    public void setViewVisibility(boolean bool) {
        if(bool) {
            changeTemp.setVisibility(View.VISIBLE);
            changeCondition.setVisibility(View.VISIBLE);
            changeIcon.setVisibility(View.VISIBLE);
            changeCity.setVisibility(View.VISIBLE);
            changeDescription.setVisibility(View.VISIBLE);
            changeDate.setVisibility(View.VISIBLE);
            descriptionTxt.setVisibility(View.VISIBLE);
            dateTxt.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.VISIBLE);
        }
        else {
            changeTemp.setVisibility(View.INVISIBLE);
            changeCondition.setVisibility(View.INVISIBLE);
            changeIcon.setVisibility(View.INVISIBLE);
            changeCity.setVisibility(View.INVISIBLE);
            changeDescription.setVisibility(View.INVISIBLE);
            changeDate.setVisibility(View.INVISIBLE);
            descriptionTxt.setVisibility(View.INVISIBLE);
            dateTxt.setVisibility(View.INVISIBLE);
            refresh.setVisibility(View.INVISIBLE);
        }
    }

    //Changes 
    public void changeIconImage(String iconCode) {
        Picasso.get().load("http://openweathermap.org/img/w/" + iconCode + ".png").into(changeIcon);
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
            setDataToViews(0);
            Toast.makeText(getApplicationContext(),"Weather Found", Toast.LENGTH_SHORT).show();
        }
    }

    //Sets data to interface of APP
    public void setDataToViews(int listNum) {
        int tmp = (int) data.getList().get(listNum).getMain().getTemp(); //HOW TO ACCESS THE DATA!!!!!!!!!!!!
        changeCity.setText(data.getCity().getName());
        changeIconImage(data.getList().get(listNum).getWeather().get(0).getIcon());
        changeCondition.setText(data.getList().get(listNum).getWeather().get(0).getMain());
        changeTemp.setText(""+tmp + " " + (char) 0x00B0+"C");
        changeDescription.setText(data.getList().get(listNum).getWeather().get(0).getDescription());
        changeDate.setText(formatDate(data.getList().get(listNum).getDtTxt().substring(0,10)));
        setViewVisibility(true);

    }

    //Changes the format of date from (yyyy-mm-dd) to (mmmm dd, yyyy)
    public String formatDate(String date) {
        //Extracts only necessary information from date
        String dateStr = date.substring(0,10);

        //Prep for date format change
        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = null;
        //Changes the date to data the SimpleDateFormat can understand
        try {
            dateObj = curFormater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Changes the date to desired format
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
        String newDateStr = postFormater.format(dateObj);

        return newDateStr;
    }

}
