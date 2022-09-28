package com.frontend.holydaystravel.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualWeather {
    private double temperature;
    private double minTemperature;
    private double maxTemperature;
    private double feelsLike;
}
