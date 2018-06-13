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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

    /*
    Kuwait's ID: 285570
    TestKey: 361e3863fb571305e306d4be3472954b
    api.openweathermap.org/data/2.5/weather?id=285570&appid=361e3863fb571305e306d4be3472954b


        State of Kuwait

    Weather Conditons: Dust
    Temperture: 321.15 K
    */

    //Variables
    public static final String TAG = "MoLog:";
    private final String url = "http://api.openweathermap.org/data/2.5/weather?id=285570&appid=361e3863fb571305e306d4be3472954b";
    JSONObject data = null;
    int temp = 0;
    TextView changeTemp;

    //Executes the getJson command as soon as the app activity is created
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        changeTemp = findViewById(R.id.tempValue);
        getJSON("Kuwait City");

    }

    //Reads the URL and extracts the JSON file to a local variable
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getJSON(final String city) {

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> execute = new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=361e3863fb571305e306d4be3472954b");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuffer json = new StringBuffer(1024);
                    String tmp = "";

                    while ((tmp = reader.readLine()) != null)
                        json.append(tmp).append("\n");
                    reader.close();

                    data = new JSONObject(json.toString());
                    Log.i(TAG,"attempted to get data");

                    String extract1;
                    //String extract2 = "";
                    double temperature = 0.0;
                    try {
                        extract1 = data.getString("main").substring(data.getString("main").indexOf("\"temp")+7,data.getString("main").indexOf("\"temp")+13);
                        temperature = Double.parseDouble(extract1);
                        temp = (int) (temperature - 273.15) ;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG,"got data");
                    Log.i(TAG,""+temp);

                    if (data.getInt("cod") != 200) {
                        System.out.println("Cancelled");
                        return null;
                    }


                } catch (Exception e) {

                    System.out.println("Exception " + e.getMessage());
                    return null;
                }

                return null;
            }

            //Prints the data extracted to the app
            @Override
            protected void onPostExecute(Void Void) {
                if (data != null) {
                    Log.d(TAG, data.toString());
                    String convert = Integer.toString(temp);
                    changeTemp.setText(convert + " " + (char) 0x00B0+"C");
                    Log.d(TAG,convert);
                }

            }
        }.execute();


    }

}
