package com.frontend.holydaystravel.service;

import com.frontend.holydaystravel.client.TourClient;
import com.frontend.holydaystravel.dto.TourDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private static TourService tourService;
    private final TourClient tourClient;

    public static TourService getInstance() {
        if (tourService == null) {
            tourService = new TourService(TourClient.getInstance());
        }
        return tourService;
    }

    public List<TourDto> getAllTours() {
        return tourClient.getAllTours();
    }

    public void saveTour(TourDto tourDto) {
        tourClient.saveTour(tourDto);
    }

    public void updateTour(TourDto tourDto) {
        tourClient.updateTour(tourDto);
    }

    public void deleteTour(Long tourId) {
        tourClient.deleteTour(tourId);
    }

    public void deleteAllTours() {
        for (TourDto tourDto : tourService.getAllTours()) {
            tourService.deleteTour(tourDto.getTourId());
        }
    }
}
