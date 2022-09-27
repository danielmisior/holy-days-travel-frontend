package com.frontend.holydaystravel.service;

import com.frontend.holydaystravel.client.HotelClient;
import com.frontend.holydaystravel.dto.HotelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private static HotelService hotelService;
    private final HotelClient hotelClient;

    public static HotelService getInstance() {
        if (hotelService == null) {
            hotelService = new HotelService(HotelClient.getInstance());
        }
        return hotelService;
    }

    public List<HotelDto> getAllHotels() {
        return hotelClient.getAllHotels();
    }

    public HotelDto getHotel(Long hotelId) {
        return hotelClient.getHotel(hotelId);
    }

    public void saveHotel(HotelDto hotelDto) {
        hotelClient.saveHotel(hotelDto);
    }

    public void updateHotel(HotelDto hotelDto) {
        hotelClient.updateHotel(hotelDto);
    }

    public void deleteHotel(Long hotelId) {
        hotelClient.deleteHotel(hotelId);
    }

    public void deleteAllHotels() {
        for (HotelDto hotelDto : hotelService.getAllHotels()) {
            hotelService.deleteHotel(hotelDto.getHotelId());
        }
    }

    public void prepareHotels() {
        HotelDto hotel1 = HotelDto.builder().hotelName("Italiana Hotels Florence").country("Italy")
                .address("Viale Europa, 205, 50126 Florence FI, Italy").stars(4).pricePerNight(84.93).nightsNumber(4).build();
        HotelDto hotel2 = HotelDto.builder().hotelName("Hotel Kursaal").country("Italy")
                .address("Via Raffaele Ruggiero, 289, 80125 Napoli NA, Italy").stars(3).pricePerNight(63.14).nightsNumber(5).build();
        HotelDto hotel3 = HotelDto.builder().hotelName("Hilton Garden Inn Paris Massy").country("France")
                .address("35 Av. Carnot, 91300 Massy, France").stars(4).pricePerNight(101.65).nightsNumber(3).build();
        HotelDto hotel4 = HotelDto.builder().hotelName("Residhome Marseille").country("France")
                .address("23 Rue Mazenod, 13002 Marseille, France").stars(4).pricePerNight(100.17).nightsNumber(4).build();
        HotelDto hotel5 = HotelDto.builder().hotelName("ILUNION Barcelona").country("Spain")
                .address("C. de Ramon Turró, 196, 198, 08005 Barcelona, Spain").stars(4).pricePerNight(160.15).nightsNumber(5).build();
        HotelDto hotel6 = HotelDto.builder().hotelName("Erase un Hotel").country("Spain")
                .address("C. de Bravo Murillo, 304, 28020 Madrid, Spain").stars(3).pricePerNight(55.14).nightsNumber(3).build();
        HotelDto hotel7 = HotelDto.builder().hotelName("Tune Hotel").country("England")
                .address("Queen Bldg, Castle St, Liverpool L2 4XE, England").stars(3).pricePerNight(111.54).nightsNumber(4).build();
        HotelDto hotel8 = HotelDto.builder().hotelName("Tavistock Hotel").country("England")
                .address("48-55 Tavistock Square, London WC1H 9EU, England").stars(3).pricePerNight(171.10).nightsNumber(2).build();
        HotelDto hotel9 = HotelDto.builder().hotelName("HI New York City Hostel").country("USA")
                .address("891 Amsterdam Ave, New York, NY 10025, USA").stars(3).pricePerNight(257.80).nightsNumber(4).build();
        HotelDto hotel10 = HotelDto.builder().hotelName("Hyatt Place St. Paul/Downtown").country("USA")
                .address("180 E Kellogg Blvd, St. Paul, MN 55101, USA").stars(3).pricePerNight(164.78).nightsNumber(3).build();
        saveHotel(hotel1);
        saveHotel(hotel2);
        saveHotel(hotel3);
        saveHotel(hotel4);
        saveHotel(hotel5);
        saveHotel(hotel6);
        saveHotel(hotel7);
        saveHotel(hotel8);
        saveHotel(hotel9);
        saveHotel(hotel10);
    }
}
