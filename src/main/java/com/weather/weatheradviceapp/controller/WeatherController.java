package com.weather.weatheradviceapp.controller;

import com.weather.weatheradviceapp.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;

    }

    @GetMapping("/weather")
    public ResponseEntity getWeatherAdvice(@RequestParam String city) {
        try {
            return ResponseEntity.ok(weatherService.getWeather(city));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
