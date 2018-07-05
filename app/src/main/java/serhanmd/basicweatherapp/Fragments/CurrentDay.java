package serhanmd.basicweatherapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import serhanmd.basicweatherapp.JSONArrayExtractor;
import serhanmd.basicweatherapp.R;
import serhanmd.basicweatherapp.WeatherData.Data;
import static serhanmd.basicweatherapp.WeatherUtils.*;

public class CurrentDay extends Fragment {
    private final String TAG = "MoLog:";
    private TextView changeTemp;
    private TextView changeCity;
    private TextView changeCondition;
    private TextView changeDescription;
    private TextView changeDate;
    private TextView descriptionTxt;
    private TextView dateTxt;
    private ImageView changeIcon;
    private Button refresh;
    private Data data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"CurrentDay Started."); //CHANGE THIS
        View view = inflater.inflate(R.layout.current_day_layout, container, false);
        changeTemp =  view.findViewById(R.id.tempValue);
        changeCondition = view.findViewById(R.id.condText);
        changeIcon = view.findViewById(R.id.icon);
        changeCity = view.findViewById(R.id.cityName);
        changeDescription = view.findViewById(R.id.descValue);
        changeDate = view.findViewById(R.id.dateValue);
        descriptionTxt = view.findViewById(R.id.descText);
        dateTxt = view.findViewById(R.id.dateText);
        refresh = view.findViewById(R.id.refreshButton);
        Log.d(TAG,"CurrentDay: Found all views."); //CHANGE THIS

        setDataToViews(0); //Change THIS

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONArrayExtractor().execute();
            }
        });

        return view;
    }

    //Sets data to interface of APP
    public void setDataToViews(int listNum) {
        data = receiveData();
        //Log.d(TAG,"Data Received");
        int tmp = (int) data.getList().get(listNum).getMain().getTemp(); //HOW TO ACCESS THE DATA!!!!!!!!!!!!
        //Log.d(TAG,"Created new view");
        changeCity.setText(data.getCity().getName());
        //Log.d(TAG,"Text Changed: "+changeCity.getText());
        Picasso.get().load("http://openweathermap.org/img/w/" + data.getList().get(listNum).getWeather().get(0).getIcon()  + ".png").into(changeIcon);
        changeCondition.setText(data.getList().get(listNum).getWeather().get(0).getMain());
        changeTemp.setText(""+tmp + " " + (char) 0x00B0+"C");
        changeDescription.setText(capitalizeEveryWord(data.getList().get(listNum).getWeather().get(0).getDescription()));
        changeDate.setText(formatDate(data.getList().get(listNum).getDtTxt().substring(0,10)));
    }
}
