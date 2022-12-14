package com.frontend.holydaystravel;

import com.frontend.holydaystravel.dto.FlightDto;
import com.frontend.holydaystravel.dto.HotelDto;
import com.frontend.holydaystravel.dto.OpenTrip.InterestingPlace;
import com.frontend.holydaystravel.dto.TourDto;
import com.frontend.holydaystravel.dto.weather.ActualWeather;
import com.frontend.holydaystravel.place.DestinationPlace;
import com.frontend.holydaystravel.place.InitiatoryPlace;
import com.frontend.holydaystravel.service.*;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBox.ItemFilter;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.LocalTime;

@Route
@PageTitle("Holy-Days Travel")
public class MainView extends VerticalLayout {
    private Grid<TourDto> tourGrid = new Grid<>(TourDto.class);
    private Grid<FlightDto> flightGrid = new Grid<>(FlightDto.class);
    private Grid<HotelDto> hotelGrid = new Grid<>(HotelDto.class);
    private Grid<ActualWeather> weatherGrid = new Grid<>(ActualWeather.class);
    private Grid<InterestingPlace> placeGrid = new Grid<>(InterestingPlace.class);

    private TourService tourService = TourService.getInstance();
    private HotelService hotelService = HotelService.getInstance();
    private WeatherService weatherService = WeatherService.getInstance();
    private OpenTripService placeService = OpenTripService.getInstance();
    private FlightService flightService = FlightService.getInstance();

    private ComboBox<InitiatoryPlace> initiatory = new ComboBox<>("Initiatory place");
    private ComboBox<DestinationPlace> destination = new ComboBox<>("Destination place");
    private ComboBox<HotelDto> hotels = new ComboBox<>("Hotel");
    private ComboBox<Integer> nightsNumber = new ComboBox<>("Number of nights");

    private DatePicker departureDate = new DatePicker("Departure date");
    private DatePicker returnDate = new DatePicker("Return date");

    private Button clean = new Button("Clean database");
    private Button prepare = new Button("Prepare database");
    private Button addTour = new Button("Add tour");
    private Button deleteTour = new Button("Delete tour");
    private Long tourId;

    public MainView() {
        if (hotelService.getAllHotels().size() == 0) {
            hotelService.prepareHotels();
        }

        configureBoxes();
        configureDates();
        configureTourGrid();
        configureHotelGrid();
        configureFlightGrid();
        configureWeatherGrid();
        configurePlaceGrid();
        configureCleanButton();
        configureDatabasePreparingButton();
        configureAddTourButton();
        configureDeleteTourButton();

        HorizontalLayout boxes = new HorizontalLayout(initiatory, destination, hotels, nightsNumber);
        HorizontalLayout dates = new HorizontalLayout(departureDate, returnDate);
        HorizontalLayout databaseButtons = new HorizontalLayout(clean, prepare);

        Text moreDetails = new Text("Click on tour to see more details");
        add(boxes, dates, new HorizontalLayout(addTour, deleteTour), moreDetails, new HorizontalLayout(tourGrid, placeGrid),
                new HorizontalLayout(hotelGrid, flightGrid), new HorizontalLayout(weatherGrid), databaseButtons);
        hotels.setItemLabelGenerator(HotelDto::getHotelName);
    }

    private void configureDeleteTourButton() {
        deleteTour.addClickListener(e -> {
            tourService.deleteTour(tourId);
            tourGrid.setItems(tourService.getAllTours());
            hotelGrid.setVisible(false);
            flightGrid.setVisible(false);
            weatherGrid.setVisible(false);
            placeGrid.setVisible(false);
        });
    }

    private void configureAddTourButton() {
        addTour.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addTour.addClickListener(e -> {
            updateHotel();
            createTour();
            tourGrid.setItems(tourService.getAllTours());
        });
    }

    private void updateHotel() {
        HotelDto hotelToUpdated = hotels.getValue();
        hotelToUpdated.setNightsNumber(nightsNumber.getValue());
        hotelService.updateHotel(hotelToUpdated);
    }

    private void createTour() {
        Double tourPrice = hotels.getValue().getPricePerNight() * hotels.getValue().getNightsNumber() + 1300;
        TourDto tourDto = TourDto.builder()
                .tourPrice(tourPrice)
                .initiatoryPlace(initiatory.getValue().toString())
                .destinationPlace(destination.getValue().toString())
                .departureDate(departureDate.getValue())
                .returnDate(returnDate.getValue())
                .flightId(createFlight().getFlightId())
                .hotelId(hotels.getValue().getHotelId())
                .build();
        tourService.saveTour(tourDto);
    }

    private FlightDto createFlight() {
        FlightDto flightDto = FlightDto.builder()
                .departureAirport(initiatory.getValue().toString() + " Central Airport")
                .arrivalAirport(destination.getValue().toString() + " Central Airport")
                .scheduledDeparture(departureDate.getValue().atTime(LocalTime.now()))
                .scheduledReturn(returnDate.getValue().atTime(LocalTime.now().plusHours(3)))
                .build();
        return flightService.saveFlight(flightDto);
    }

    private void configureDatabasePreparingButton() {
        prepare.addClickListener(e -> {
            if (hotelService.getAllHotels().size() == 0) {
                hotelService.prepareHotels();
            }
            add(new Text("The database has been prepared!"));
        });
    }

    private void configureCleanButton() {
        clean.addClickListener(e -> {
            tourService.deleteAllTours();
            hotelService.deleteAllHotels();
            flightService.deleteAllFLights();
            tourGrid.setItems(tourService.getAllTours());
            hotelGrid.setVisible(false);
            flightGrid.setVisible(false);
            weatherGrid.setVisible(false);
            placeGrid.setVisible(false);
        });
    }

    private void configureTourGrid() {
        tourGrid.addClassName("tour-grid");
        tourGrid.setWidth("800px");
        tourGrid.setVisible(true);
        tourGrid.setItems(tourService.getAllTours());
        tourGrid.setColumns("initiatoryPlace", "destinationPlace", "departureDate", "returnDate", "tourPrice");
        tourGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        tourGrid.addItemClickListener(e -> tourId = e.getItem().getTourId());
    }

    private void configureHotelGrid() {
        hotelGrid.setWidth("800px");
        hotelGrid.setVisible(false);
        hotelGrid.setAllRowsVisible(true);
        hotelGrid.setColumns("hotelName", "address", "stars", "pricePerNight");
        hotelGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        tourGrid.addItemClickListener(i -> {
            hotelGrid.setVisible(true);
            hotelGrid.setItems(hotelService.getHotel(i.getItem().getHotelId()));
        });
    }

    private void configureFlightGrid() {
        flightGrid.setWidth("800px");
        flightGrid.setVisible(false);
        flightGrid.setAllRowsVisible(true);
        flightGrid.setColumns("departureAirport", "arrivalAirport", "scheduledDeparture", "scheduledReturn");
        flightGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        tourGrid.addItemClickListener(i -> {
            flightGrid.setVisible(true);
            flightGrid.setItems(flightService.getFlight(i.getItem().getFlightId()));
        });
    }

    private void configureWeatherGrid() {
        weatherGrid.setWidth("800px");
        weatherGrid.setVisible(false);
        weatherGrid.setAllRowsVisible(true);
        weatherGrid.setColumns("temperature", "minTemperature", "maxTemperature", "feelsLike");
        weatherGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        tourGrid.addItemClickListener(i -> {
            weatherGrid.setVisible(true);
            weatherGrid.setItems(weatherService.getActualWeather(i.getItem().getDestinationPlace()));
        });
    }

    private void configurePlaceGrid() {
        Image image = new Image();
        image.setWidth("100px");

        placeGrid.setWidth("800px");
        placeGrid.setVisible(false);
        placeGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        placeGrid.setColumns("name", "text");
        placeGrid.addColumn(new ComponentRenderer<>(interestingPlace ->
                new Image(interestingPlace.getImage(), "image"))).setHeader("Photo");
        tourGrid.addItemClickListener(i -> {
                placeGrid.setVisible(true);
                placeGrid.setItems(placeService.getInterestingPlaces(i.getItem().getDestinationPlace()));
        });
    }

    private void configureDates() {
        departureDate.setMin(LocalDate.now());
        departureDate.addValueChangeListener(e -> returnDate.setMin(e.getValue()));
        returnDate.addValueChangeListener(e -> departureDate.setMax(e.getValue()));
    }

    private void configureBoxes() {
        initiatory.setItems(InitiatoryPlace.values());
        destination.setItems(DestinationPlace.values());
        nightsNumber.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        destination.addValueChangeListener(e -> {
            ItemFilter<HotelDto> filter = (hotelDto, string) -> hotelDto.getAddress().contains(destination.getValue().toString());
            hotels.setItems(filter, hotelService.getAllHotels());
        });
    }
}