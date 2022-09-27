package com.frontend.holydaystravel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private Long flightId;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime scheduledDeparture;
    private LocalDateTime scheduledReturn;
}
