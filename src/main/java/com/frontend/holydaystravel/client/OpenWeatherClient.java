package com.frontend.holydaystravel.client;

import com.frontend.holydaystravel.dto.weather.WeatherDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class OpenWeatherClient {
    private static OpenWeatherClient openWeatherClient;
    private final RestTemplate restTemplate;
    private static final String WEATHER_URL = "http://localhost:8888/v1/weather/";

    public static OpenWeatherClient getInstance() {
        if (openWeatherClient == null) {
            openWeatherClient = new OpenWeatherClient(new RestTemplate());
        }
        return openWeatherClient;
    }

    public WeatherDto getActualWeather(String cityName) {
        try {
            return restTemplate.getForObject(WEATHER_URL + cityName, WeatherDto.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}