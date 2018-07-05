package serhanmd.basicweatherapp;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import serhanmd.basicweatherapp.WeatherData.Data;

public class WeatherUtils {

    private static Data data;

    //Changes the format of date from (yyyy-mm-dd) to (mmmm dd, yyyy)
    public static String formatDate(String date) {
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

    public static void sendData(Data dataFromJSON) {
        data = dataFromJSON;
    }

    public static Data receiveData() {
        return data;
    }

}
