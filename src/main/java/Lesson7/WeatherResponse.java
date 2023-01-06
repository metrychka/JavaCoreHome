package Lesson7;

public class WeatherResponse {
    private String city;
    private String EffectiveDate;
    private double temperature;


    public WeatherResponse() {
    }

    public WeatherResponse(String city, String EffectiveDate, double temperature) {
        this.city = city;
        this.EffectiveDate = EffectiveDate;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return EffectiveDate;
    }

    public void setLocalDate(String localDate) {
        this.EffectiveDate = localDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "city='" + city + '\'' +
                ", localDate='" + EffectiveDate + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
