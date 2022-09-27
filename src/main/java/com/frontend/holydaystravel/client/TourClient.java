package com.frontend.holydaystravel.client;

import com.frontend.holydaystravel.dto.TourDto;
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
public class TourClient {
    private final RestTemplate restTemplate;
    private static final String TOUR_URL = "http://localhost:8888/v1/tour/";
    private static TourClient tourClient;

    public static TourClient getInstance() {
        if (tourClient == null) {
            tourClient = new TourClient(new RestTemplate());
        }
        return tourClient;
    }

    public List<TourDto> getAllTours() {
        try {
            ResponseEntity<TourDto[]> responseEntity = restTemplate.getForEntity(TOUR_URL, TourDto[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void saveTour(TourDto tourDto) {
        try {
            restTemplate.postForObject(TOUR_URL, tourDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateTour(TourDto tourDto) {
        try {
            restTemplate.put(TOUR_URL, tourDto, Void.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteTour(Long tourId) {
        try {
            restTemplate.delete(TOUR_URL + tourId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
