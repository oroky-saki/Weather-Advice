package com.weather.weatheradviceapp.service;

import com.weather.weatheradviceapp.util.JsonReader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherService {

    private final AdviceService adviceService;
    private final JsonReader jsonReader;

    public WeatherService(AdviceService adviceService, JsonReader jsonReader) {
        this.adviceService = adviceService;
        this.jsonReader = jsonReader;
    }

    public String getWeather(String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?units=metric&q=" + city + "&appid=bb15b57430e904cbcca33b5bf93be01a";

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .callTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build();

        String responseBody = "Default response body by OPENWEATHER";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                //responseBody = response.body().string();
                String weatherCondition = jsonReader.readWeather(response.body().string());
                responseBody = adviceService.getAdvice(weatherCondition);
                return responseBody;
            }
        } catch (Exception e) {

            return e.getMessage();
        }

        return responseBody;
    }
}
