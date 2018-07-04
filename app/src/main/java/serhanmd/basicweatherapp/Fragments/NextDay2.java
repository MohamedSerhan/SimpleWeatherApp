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

import serhanmd.basicweatherapp.MainPage;
import serhanmd.basicweatherapp.R;

public class NextDay2 extends Fragment {
    private static final String TAG = "CurrentDay";
    private TextView changeTemp;
    private TextView changeCity;
    private TextView changeCondition;
    private TextView changeDescription;
    private TextView changeDate;
    private TextView descriptionTxt;
    private TextView dateTxt;
    private ImageView changeIcon;
    private Button refresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.next_day_2, container, false);
        changeTemp = view.findViewById(R.id.tempValue);
        changeCondition = view.findViewById(R.id.condText);
        changeIcon = view.findViewById(R.id.icon);
        changeCity = view.findViewById(R.id.cityName);
        changeDescription = view.findViewById(R.id.descValue);
        changeDate = view.findViewById(R.id.dateValue);
        descriptionTxt = view.findViewById(R.id.descText);
        dateTxt = view.findViewById(R.id.dateText);
        refresh = view.findViewById(R.id.refreshButton);
        Log.d(TAG,"onCreateView: Stated.");

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new MainPage.JSONFeedArrayTask().execute();
            }
        });

        return view;
    }
}
