package com.frontend.holydaystravel.client;

import com.frontend.holydaystravel.dto.HotelDto;
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
public class HotelClient {
    private final RestTemplate restTemplate;
    private static final String HOTEL_URL = "http://localhost:8888/v1/hotel/";
    private static HotelClient hotelClient;

    public static HotelClient getInstance() {
        if (hotelClient == null) {
            hotelClient = new HotelClient(new RestTemplate());
        }
        return hotelClient;
    }

    public List<HotelDto> getAllHotels() {
        try {
            ResponseEntity<HotelDto[]> responseEntity = restTemplate.getForEntity(HOTEL_URL, HotelDto[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public HotelDto getHotel(Long hotelId) {
        try {
            return restTemplate.getForObject(HOTEL_URL + hotelId, HotelDto.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void saveHotel(HotelDto hotelDto) {
        try {
            restTemplate.postForObject(HOTEL_URL, hotelDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateHotel(HotelDto hotelDto) {
        try {
            restTemplate.put(HOTEL_URL, hotelDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteHotel(Long hotelId) {
        try {
            restTemplate.delete(HOTEL_URL + hotelId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
