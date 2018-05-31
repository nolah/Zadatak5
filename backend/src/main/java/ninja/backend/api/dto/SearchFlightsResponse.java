package ninja.backend.api.dto;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class SearchFlightsResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<List<SearchFlightDto>> DEPARTING_FLIGHTS = new PropertyPath<>("departingFlights");
    public static final PropertyPath<List<SearchFlightDto>> RETURNING_FLIGHTS = new PropertyPath<>("returningFlights");

    @Valid
    private List<SearchFlightDto> departingFlights = new ArrayList<>();

    @Valid
    private List<SearchFlightDto> returningFlights = new ArrayList<>();

    private SearchFlightsResponse() {
    }

    public SearchFlightsResponse(List<SearchFlightDto> departingFlights, List<SearchFlightDto> returningFlights) {
        this.departingFlights = departingFlights;
        this.returningFlights = returningFlights;
    }

    public List<SearchFlightDto> getDepartingFlights() {
        return departingFlights;
    }

    public List<SearchFlightDto> getReturningFlights() {
        return returningFlights;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SearchFlightsResponse other = (SearchFlightsResponse) obj;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        return result;
    }

    @Override
    public String toString() {
        return "SearchFlightsResponse[" + "this.departingFlights=" + this.departingFlights + ", this.returningFlights=" + this.returningFlights + "]";
    }

}
