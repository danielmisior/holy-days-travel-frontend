package com.frontend.holydaystravel.dto.OpenTrip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoCoordsDto {
    private String name;
    private String country;
    private double lat;
    private double lon;
    private int population;
    private String timezone;
}
