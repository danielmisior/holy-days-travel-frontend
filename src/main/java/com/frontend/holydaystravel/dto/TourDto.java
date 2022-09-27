package com.frontend.holydaystravel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourDto {
    private Long tourId;
    private Double tourPrice;
    private String initiatoryPlace;
    private String destinationPlace;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Long flightId;
    private Long hotelId;
}
