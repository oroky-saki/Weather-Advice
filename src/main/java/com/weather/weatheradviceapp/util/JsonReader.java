package com.weather.weatheradviceapp.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weatheradviceapp.model.ChatResponse;
import com.weather.weatheradviceapp.model.WeatherData;
import org.springframework.stereotype.Component;

@Component
public class JsonReader {

    public String readContent(String jsonString) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String content = "Default Content by GPT";
        try {
            ChatResponse chatResponse = objectMapper.readValue(jsonString, ChatResponse.class);
            content = chatResponse.getChoices()[0].getMessage().getContent();
            return content;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public String readWeather(String jsonString) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String weatherCondition = "Default Weather Condition by GTP Advice";

        try {

            WeatherData weatherData = objectMapper.readValue(jsonString, WeatherData.class);

            String description = "";

            try {
                JsonNode rootNode = objectMapper.readTree(jsonString);
                JsonNode weatherNode = rootNode.get("weather");

                if (weatherNode.isArray() && weatherNode.size() > 0) {
                    description = weatherNode.get(0).get("description").asText();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            double temperature = weatherData.getMain().getTemp();
            double feelsLike = weatherData.getMain().getFeelsLike();
            int humidity = weatherData.getMain().getHumidity();
            double windSpeed = weatherData.getWind().getSpeed();
            int cloudsAll = weatherData.getClouds().getAll();
            String city = weatherData.getName();

            weatherCondition = "Погода в городе " + city + ": " + description + "Температура на улице составляет: " + temperature + " градусов цельсия" + ", которая ощущается как " + feelsLike
                    + ". Влажность воздуха при этом составляет " + humidity + ". Скорость ветра равна " + windSpeed + " метров в секунду."
                    + " Объем дождя за последний час составляет " + " миллиметров." + "Облачность равна " + cloudsAll + " процентов";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherCondition;
    }
}
