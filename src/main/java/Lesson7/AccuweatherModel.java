package Lesson7;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccuweatherModel implements WeatherModel {

    private static final String PROTOCOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAY = "5day";
    private static final String API_KEY = "ccCWHYp87mqeMLKMw0qvQusGh4aXGnPx";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    public static final ObjectMapper objectMapper = new ObjectMapper();

    private DBRepositotry dbRepositotry = new DBRepositotry();

    public void getWeather(String selectedCity, Period period) throws IOException {
        switch (period) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastResponse.body().string();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                Object obj = null;
                try {
                    obj = new JSONParser().parse(weatherResponse);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                JSONObject jo = (JSONObject) obj;
                JSONArray values = (JSONArray) jo.get("DailyForecasts");
                JSONObject jo1 = (JSONObject) jo.get("Headline");
                String weathdsc2 = jo1.get("Text").toString();
                for (int i = 0; i<values.size(); i++) {
                    JSONObject weather = (JSONObject) values.get(i);
                    String weathdsc3 = weather.get("Date").toString();
                    JSONObject jo2 = (JSONObject) weather.get("Temperature");
                    JSONObject jo3 = (JSONObject) jo2.get("Minimum");
                    Double fahrenheit = Double.parseDouble(jo3.get("Value").toString());
                    int celsium = (int) (((fahrenheit - 32) * 5) / 9);

                    System.out.println("Для города: " + selectedCity + "на дату: " + weathdsc3 + ", ожидается погода: " + weathdsc2 + ", температура в градусах цельсия: " + celsium);
                    try {
                        dbRepositotry.saveWeatherToDataBase(selectedCity, weathdsc3, celsium); //- тут после парсинга добавляем данные в БД
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }


                break;
            case FIVE_DAYS:
                HttpUrl httpUrl1 = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request1 = new Request.Builder()
                        .url(httpUrl1)
                        .build();

                Response fiveDayForecastResponse = okHttpClient.newCall(request1).execute();
                String weatherResponse5 = fiveDayForecastResponse.body().string();
                Object obj5 = null;
                try {
                    obj5 = new JSONParser().parse(weatherResponse5);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                JSONObject jo5 = (JSONObject) obj5;
                JSONArray values5 = (JSONArray) jo5.get("DailyForecasts");
                JSONObject jo15 = (JSONObject) jo5.get("Headline");
                String weathdsc25 = jo15.get("Text").toString();
                for (int i = 0; i<values5.size(); i++) {
                    JSONObject weather = (JSONObject) values5.get(i);
                    String weathdsc35 = weather.get("Date").toString();
                    JSONObject jo25 = (JSONObject) weather.get("Temperature");
                    JSONObject jo35 = (JSONObject) jo25.get("Minimum");
                    Double fahrenheit = Double.parseDouble(jo35.get("Value").toString());
                    int celsium = (int) (((fahrenheit - 32) * 5) / 9);

                    System.out.println("Для города: " + selectedCity + " на дату: " + weathdsc35 + ",  температура в градусах цельсия: " + celsium);
                }
                //System.out.println(weatherResponse1);
                break;

        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dbRepositotry.getSavedToDBWeather();
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }


}
