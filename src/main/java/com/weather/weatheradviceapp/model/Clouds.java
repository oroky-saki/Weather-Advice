package com.weather.weatheradviceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clouds {

    @JsonProperty("all")
    private int all;
}
