package com.frontend.holydaystravel.client;

import com.frontend.holydaystravel.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlightClient {
    private final RestTemplate restTemplate;
    private static final String FLIGHT_URL = "http://localhost:8888/v1/flight/";
    private static FlightClient flightClient;

    public static FlightClient getInstance() {
        if (flightClient == null) {
            flightClient =  new FlightClient(new RestTemplate());
        }
        return flightClient;
    }

    public List<FlightDto> getAllFlight() {
        try {
            ResponseEntity<FlightDto[]> responseEntity = restTemplate.getForEntity(FLIGHT_URL, FlightDto[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public FlightDto getFlight(Long flightId) {
        try {
            return restTemplate.getForObject(FLIGHT_URL + flightId, FlightDto.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public FlightDto saveFlight(FlightDto flightDto) {
        try {
            return restTemplate.postForObject(FLIGHT_URL, flightDto, FlightDto.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void deleteFlight(Long flightId) {
        try {
            restTemplate.delete(FLIGHT_URL + flightId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
