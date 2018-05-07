package ninja.backend.repository.tuple;

import ninja.backend.model.*;


public class AircraftAircraftsTuple {

    private final Aircraft aircraft;
    private final Airline airline;

    public AircraftAircraftsTuple(Aircraft aircraft, Airline airline) {
        this.aircraft = aircraft;
        this.airline = airline;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public Airline getAirline() {
        return airline;
    }

}