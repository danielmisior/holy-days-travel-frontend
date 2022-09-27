package com.frontend.holydaystravel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    private Long hotelId;
    private String hotelName;
    private String country;
    private String address;
    private Integer stars;
    private Double pricePerNight;
    private Integer nightsNumber;
}
