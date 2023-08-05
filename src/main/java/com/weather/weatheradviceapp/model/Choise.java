package com.weather.weatheradviceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Choise {

    @JsonProperty("index")
    private int index;


    @JsonProperty("message")
    private Message message;


    @JsonProperty("finish_reason")
    private String finishReason;
}
