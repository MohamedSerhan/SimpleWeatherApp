package serhanmd.basicweatherapp;

public class weatherDetails {

    private String temp;
    private String pressure;
    private String humidity;

    public weatherDetails(String temp, String pressure, String humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
