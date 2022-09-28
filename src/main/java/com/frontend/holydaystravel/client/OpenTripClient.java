package com.frontend.holydaystravel.client;

import com.frontend.holydaystravel.dto.OpenTrip.GeoCoordsDto;
import com.frontend.holydaystravel.dto.OpenTrip.PlacePropertiesDto;
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
public class OpenTripClient {
    private static OpenTripClient openTripClient;
    private final RestTemplate restTemplate;
    private static final String TRIP_URL = "http://localhost:8888/v1/places/";

    public static OpenTripClient getInstance() {
        if (openTripClient == null) {
            openTripClient = new OpenTripClient(new RestTemplate());
        }
        return openTripClient;
    }

    public GeoCoordsDto getCoordinates(String placeName) {
        try {
            return restTemplate.getForObject(TRIP_URL + "coords?placeName=" + placeName, GeoCoordsDto.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<PlacePropertiesDto> getInterestingPlaces(String placeName) {
        try {
            ResponseEntity<PlacePropertiesDto[]> responseEntity = restTemplate.getForEntity(TRIP_URL + "interesting?placeName=" + placeName, PlacePropertiesDto[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
