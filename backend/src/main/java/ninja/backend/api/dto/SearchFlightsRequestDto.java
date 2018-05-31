package ninja.backend.api.dto;

import java.io.Serializable;

import java.time.*;

import javax.validation.constraints.*;

import ninja.backend.model.enumeration.*;
import eu.execom.fabut.property.PropertyPath;


public class SearchFlightsRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<ZonedDateTime> DEPARTING_DATE = new PropertyPath<>("departingDate");
    public static final PropertyPath<ZonedDateTime> RETURNING = new PropertyPath<>("returning");
    public static final PropertyPath<String> FROM_AIRPORT = new PropertyPath<>("fromAirport");
    public static final PropertyPath<String> TO_AIRPORT = new PropertyPath<>("toAirport");
    public static final PropertyPath<Integer> TRAVELERS = new PropertyPath<>("travelers");
    public static final PropertyPath<SeatType> SEAT_TYPE = new PropertyPath<>("seatType");

    @NotNull
    private ZonedDateTime departingDate;

    @NotNull
    private ZonedDateTime returning;

    @NotNull
    @Size(min = 2, max = 100)
    private String fromAirport;

    @NotNull
    @Size(min = 2, max = 100)
    private String toAirport;

    @NotNull
    @Min(1)
    @Max(500)
    private Integer travelers;

    @NotNull
    private SeatType seatType;

    private SearchFlightsRequestDto() {
    }

    public SearchFlightsRequestDto(ZonedDateTime departingDate, ZonedDateTime returning, String fromAirport, String toAirport, Integer travelers, SeatType seatType) {
        this.departingDate = departingDate;
        this.returning = returning;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.travelers = travelers;
        this.seatType = seatType;
    }

    public ZonedDateTime getDepartingDate() {
        return departingDate;
    }

    public ZonedDateTime getReturning() {
        return returning;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public Integer getTravelers() {
        return travelers;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SearchFlightsRequestDto other = (SearchFlightsRequestDto) obj;
        if (this.departingDate != null && other.departingDate != null && !this.departingDate.equals(other.departingDate))
            return false;
        if (this.returning != null && other.returning != null && !this.returning.equals(other.returning))
            return false;
        if (this.fromAirport != null && other.fromAirport != null && !this.fromAirport.equals(other.fromAirport))
            return false;
        if (this.toAirport != null && other.toAirport != null && !this.toAirport.equals(other.toAirport))
            return false;
        if (this.travelers != null && other.travelers != null && !this.travelers.equals(other.travelers))
            return false;
        if (this.seatType != null && other.seatType != null && !this.seatType.equals(other.seatType))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.departingDate == null) ? 0 : this.departingDate.hashCode());
        result = prime * result + ((this.returning == null) ? 0 : this.returning.hashCode());
        result = prime * result + ((this.fromAirport == null) ? 0 : this.fromAirport.hashCode());
        result = prime * result + ((this.toAirport == null) ? 0 : this.toAirport.hashCode());
        result = prime * result + ((this.travelers == null) ? 0 : this.travelers.hashCode());
        result = prime * result + ((this.seatType == null) ? 0 : this.seatType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SearchFlightsRequestDto[" + "this.departingDate=" + this.departingDate + ", this.returning=" + this.returning + ", this.fromAirport=" + this.fromAirport + ", this.toAirport="
                + this.toAirport + ", this.travelers=" + this.travelers + ", this.seatType=" + this.seatType + "]";
    }

}
