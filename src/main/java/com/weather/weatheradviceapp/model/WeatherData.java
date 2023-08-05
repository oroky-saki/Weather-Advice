package com.weather.weatheradviceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherData {

    @JsonProperty("weather")
    private Weather[] weather;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("wind")
    private Wind wind;


    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("name")
    private String name;
}
