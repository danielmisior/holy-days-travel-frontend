package com.frontend.holydaystravel.place;

public enum DestinationPlace {
    FLORENCE("Florence"), NAPOLI("Napoli"), MASSY("Massy"),
    MARSEILLE("Marseille"), BARCELONA("Barcelona"),
    MADRID("Madrid"), LIVERPOOL("Liverpool"), LONDON("London"),
    NEW_YORK("New York"), ST_POUL("St. Paul");

    private String destination;
    DestinationPlace(String destination) {
        this.destination = destination;
    }


    @Override
    public String toString() {
        return destination;
    }
}
