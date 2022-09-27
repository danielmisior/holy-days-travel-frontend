package com.frontend.holydaystravel.service;

import com.frontend.holydaystravel.client.FlightClient;
import com.frontend.holydaystravel.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private static FlightService flightService;
    private final FlightClient flightClient;

    public static FlightService getInstance() {
        if (flightService == null) {
            flightService = new FlightService(FlightClient.getInstance());
        }
        return flightService;
    }

    public List<FlightDto> getAllFlight() {
        return flightClient.getAllFlight();
    }

    public FlightDto getFlight(Long flightId) {
        return flightClient.getFlight(flightId);
    }
    public FlightDto saveFlight(FlightDto flightDto) {
        return flightClient.saveFlight(flightDto);
    }

    public void deleteFlight(Long flightId) {
        flightClient.deleteFlight(flightId);
    }

    public void deleteAllFLights() {
        for (FlightDto flightDto : flightService.getAllFlight()) {
            flightService.deleteFlight(flightDto.getFlightId());
        }
    }
}