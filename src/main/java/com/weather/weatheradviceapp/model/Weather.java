package com.weather.weatheradviceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

}
