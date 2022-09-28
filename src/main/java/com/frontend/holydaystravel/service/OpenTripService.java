package com.frontend.holydaystravel.service;

import com.frontend.holydaystravel.client.OpenTripClient;
import com.frontend.holydaystravel.dto.OpenTrip.InterestingPlace;
import com.frontend.holydaystravel.dto.OpenTrip.PlacePropertiesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpenTripService {
    private static OpenTripService openTripService;
    private final OpenTripClient openTripClient;

    public static OpenTripService getInstance() {
        if (openTripService == null) {
            openTripService = new OpenTripService(OpenTripClient.getInstance());
        }
        return openTripService;
    }

    public List<InterestingPlace> getInterestingPlaces(String placeName) {
        List<PlacePropertiesDto> places = openTripClient.getInterestingPlaces(placeName);
        List<InterestingPlace> interestingPlaces = places.stream()
                .map(p -> InterestingPlace.builder()
                        .name(p.getName())
                        .text(p.getWiki().getText())
                        .image(p.getPreview().getSource())
                        .build())
                .collect(Collectors.toList());
        return interestingPlaces;
    }
}
