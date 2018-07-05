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
import serhanmd.basicweatherapp.Fragments.*;
import serhanmd.basicweatherapp.WeatherData.Data;


public class MainPage extends AppCompatActivity {

    //Variables
    private final String TAG = "MoLog:";
    TextView changeTemp;
    TextView changeCity;
    TextView changeCondition;
    TextView changeDescription;
    TextView changeDate;
    TextView descriptionTxt;
    TextView dateTxt;
    ImageView changeIcon;
    Button refresh;
//    static Gson gson = new Gson();
//    static Data data;
    private SectionsStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;

    //Executes whatever when the activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Starts the selected layout as the first page when app opens
        setContentView(R.layout.activity_main_page);
        new JSONArrayExtractor().execute(); //This command allows the class to run and make URL requests without crashing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mSectionStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        Log.i(TAG,"MainPage Started.");
        Toast.makeText(getApplicationContext(),"Weather Found", Toast.LENGTH_SHORT).show();
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CurrentDay(), "Current Day");
        adapter.addFragment(new NextDay1(), "Next Day 1");
        adapter.addFragment(new NextDay2(), "Next Day 2");
        adapter.addFragment(new NextDay3(), "Next Day 3");
        adapter.addFragment(new NextDay4(), "Next Day 4");
        viewPager.setAdapter(adapter);
    }








    /*//Sets all the views to visible for true and invisible for false
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
    }*/

}
