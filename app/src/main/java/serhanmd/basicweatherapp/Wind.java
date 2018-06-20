package serhanmd.basicweatherapp;

public class Wind {

    private String speed;
    private String deg;

    public Wind(String speed, String deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public String getSpeed() {
        return speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }
}
