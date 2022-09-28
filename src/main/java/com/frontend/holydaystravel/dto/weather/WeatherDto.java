package com.frontend.holydaystravel.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {
    private ArrayList<Weather> weather;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
}
