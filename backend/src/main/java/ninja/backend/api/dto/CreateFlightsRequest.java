package ninja.backend.api.dto;

import java.io.Serializable;

import java.math.BigDecimal;
import java.time.*;

import javax.validation.constraints.*;

import ninja.backend.model.enumeration.*;
import eu.execom.fabut.property.PropertyPath;


public class CreateFlightsRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> AIRCRAFT_ID = new PropertyPath<>("aircraftId");
    public static final PropertyPath<ZonedDateTime> FROM_DATE = new PropertyPath<>("fromDate");
    public static final PropertyPath<ZonedDateTime> TO_DATE = new PropertyPath<>("toDate");
    public static final PropertyPath<FlightSchemeType> SCHEME_TYPE = new PropertyPath<>("schemeType");
    public static final PropertyPath<Integer> NUMBER_OF_ECONOMY_SEATS = new PropertyPath<>("numberOfEconomySeats");
    public static final PropertyPath<BigDecimal> PRICE_OF_ECONOMY_SEAT = new PropertyPath<>("priceOfEconomySeat");
    public static final PropertyPath<Integer> NUMBER_OF_BUSINESS_SEATS = new PropertyPath<>("numberOfBusinessSeats");
    public static final PropertyPath<BigDecimal> PRICE_OF_BUSINESS_SEATS = new PropertyPath<>("priceOfBusinessSeats");
    public static final PropertyPath<String> FROM_AIRPORT = new PropertyPath<>("fromAirport");
    public static final PropertyPath<String> TO_AIRPORT = new PropertyPath<>("toAirport");

    @NotNull
    private Long aircraftId;

    @NotNull
    private ZonedDateTime fromDate;

    private ZonedDateTime toDate;

    @NotNull
    private FlightSchemeType schemeType;

    @NotNull
    private Integer numberOfEconomySeats;

    @NotNull
    private BigDecimal priceOfEconomySeat;

    @NotNull
    private Integer numberOfBusinessSeats;

    @NotNull
    private BigDecimal priceOfBusinessSeats;

    @NotNull
    @Size(min = 1, max = 100)
    private String fromAirport;

    @NotNull
    @Size(min = 1, max = 100)
    private String toAirport;

    private CreateFlightsRequest() {
    }

    public CreateFlightsRequest(Long aircraftId, ZonedDateTime fromDate, ZonedDateTime toDate, FlightSchemeType schemeType, Integer numberOfEconomySeats, BigDecimal priceOfEconomySeat,
            Integer numberOfBusinessSeats, BigDecimal priceOfBusinessSeats, String fromAirport, String toAirport) {
        this.aircraftId = aircraftId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.schemeType = schemeType;
        this.numberOfEconomySeats = numberOfEconomySeats;
        this.priceOfEconomySeat = priceOfEconomySeat;
        this.numberOfBusinessSeats = numberOfBusinessSeats;
        this.priceOfBusinessSeats = priceOfBusinessSeats;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public ZonedDateTime getFromDate() {
        return fromDate;
    }

    public ZonedDateTime getToDate() {
        return toDate;
    }

    public FlightSchemeType getSchemeType() {
        return schemeType;
    }

    public Integer getNumberOfEconomySeats() {
        return numberOfEconomySeats;
    }

    public BigDecimal getPriceOfEconomySeat() {
        return priceOfEconomySeat;
    }

    public Integer getNumberOfBusinessSeats() {
        return numberOfBusinessSeats;
    }

    public BigDecimal getPriceOfBusinessSeats() {
        return priceOfBusinessSeats;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CreateFlightsRequest other = (CreateFlightsRequest) obj;
        if (this.aircraftId != null && other.aircraftId != null && !this.aircraftId.equals(other.aircraftId))
            return false;
        if (this.fromDate != null && other.fromDate != null && !this.fromDate.equals(other.fromDate))
            return false;
        if (this.toDate != null && other.toDate != null && !this.toDate.equals(other.toDate))
            return false;
        if (this.schemeType != null && other.schemeType != null && !this.schemeType.equals(other.schemeType))
            return false;
        if (this.numberOfEconomySeats != null && other.numberOfEconomySeats != null && !this.numberOfEconomySeats.equals(other.numberOfEconomySeats))
            return false;
        if (this.priceOfEconomySeat != null && other.priceOfEconomySeat != null && !this.priceOfEconomySeat.equals(other.priceOfEconomySeat))
            return false;
        if (this.numberOfBusinessSeats != null && other.numberOfBusinessSeats != null && !this.numberOfBusinessSeats.equals(other.numberOfBusinessSeats))
            return false;
        if (this.priceOfBusinessSeats != null && other.priceOfBusinessSeats != null && !this.priceOfBusinessSeats.equals(other.priceOfBusinessSeats))
            return false;
        if (this.fromAirport != null && other.fromAirport != null && !this.fromAirport.equals(other.fromAirport))
            return false;
        if (this.toAirport != null && other.toAirport != null && !this.toAirport.equals(other.toAirport))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.aircraftId == null) ? 0 : this.aircraftId.hashCode());
        result = prime * result + ((this.fromDate == null) ? 0 : this.fromDate.hashCode());
        result = prime * result + ((this.toDate == null) ? 0 : this.toDate.hashCode());
        result = prime * result + ((this.schemeType == null) ? 0 : this.schemeType.hashCode());
        result = prime * result + ((this.numberOfEconomySeats == null) ? 0 : this.numberOfEconomySeats.hashCode());
        result = prime * result + ((this.priceOfEconomySeat == null) ? 0 : this.priceOfEconomySeat.hashCode());
        result = prime * result + ((this.numberOfBusinessSeats == null) ? 0 : this.numberOfBusinessSeats.hashCode());
        result = prime * result + ((this.priceOfBusinessSeats == null) ? 0 : this.priceOfBusinessSeats.hashCode());
        result = prime * result + ((this.fromAirport == null) ? 0 : this.fromAirport.hashCode());
        result = prime * result + ((this.toAirport == null) ? 0 : this.toAirport.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CreateFlightsRequest[" + "this.aircraftId=" + this.aircraftId + ", this.fromDate=" + this.fromDate + ", this.toDate=" + this.toDate + ", this.schemeType=" + this.schemeType
                + ", this.numberOfEconomySeats=" + this.numberOfEconomySeats + ", this.priceOfEconomySeat=" + this.priceOfEconomySeat + ", this.numberOfBusinessSeats=" + this.numberOfBusinessSeats
                + ", this.priceOfBusinessSeats=" + this.priceOfBusinessSeats + ", this.fromAirport=" + this.fromAirport + ", this.toAirport=" + this.toAirport + "]";
    }

}
