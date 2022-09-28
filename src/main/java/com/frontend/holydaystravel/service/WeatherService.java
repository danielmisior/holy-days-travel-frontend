package com.frontend.holydaystravel.service;

import com.frontend.holydaystravel.client.OpenWeatherClient;
import com.frontend.holydaystravel.dto.weather.ActualWeather;
import com.frontend.holydaystravel.dto.weather.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private static WeatherService weatherService;
    private final OpenWeatherClient openWeatherClient;

    public static WeatherService getInstance() {
        if (weatherService == null) {
            weatherService = new WeatherService(OpenWeatherClient.getInstance());
        }
        return weatherService;
    }

    public ActualWeather getActualWeather(String cityName) {
        WeatherDto weatherDto = openWeatherClient.getActualWeather(cityName);
        return ActualWeather.builder()
                .temperature(weatherDto.getMain().getTemp())
                .maxTemperature(weatherDto.getMain().getTemp_max())
                .minTemperature(weatherDto.getMain().getTemp_min())
                .feelsLike(weatherDto.getMain().getFeels_like())
                .build();
    }
}
