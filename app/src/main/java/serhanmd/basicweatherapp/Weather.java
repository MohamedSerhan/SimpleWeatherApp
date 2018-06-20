package serhanmd.basicweatherapp;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("main")
    private String weatherType;
    @SerializedName("description")
    private String wDescription;

    public Weather(String weatherType, String wDescription) {
        this.weatherType = weatherType;
        this.wDescription = wDescription;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public String getwDescription() {
        return wDescription;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public void setwDescription(String wDescription) {
        this.wDescription = wDescription;
    }
}
