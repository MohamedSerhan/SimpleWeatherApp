package serhanmd.basicweatherapp;

public class WeatherData {
    private Weather weather;
    private weatherDetails details;
    private String visibility;
    private Wind wind;
    private String name;

    public WeatherData(Weather weather, weatherDetails details, String visibility, Wind wind, String name) {
        this.weather = weather;
        this.details = details;
        this.visibility = visibility;
        this.wind = wind;
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
