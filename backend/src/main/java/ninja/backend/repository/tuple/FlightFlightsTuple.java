package ninja.backend.repository.tuple;

import ninja.backend.model.*;


public class FlightFlightsTuple {

    private final Flight flight;
    private final Aircraft aircraft;
    private final Airline airline;

    public FlightFlightsTuple(Flight flight, Aircraft aircraft, Airline airline) {
        this.flight = flight;
        this.aircraft = aircraft;
        this.airline = airline;
    }

    public Flight getFlight() {
        return flight;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public Airline getAirline() {
        return airline;
    }

}