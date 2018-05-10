package ninja.backend.api.dto;

import java.io.Serializable;

import java.math.BigDecimal;
import java.time.*;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class FlightsResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<Long> AIRCRAFT_ID = new PropertyPath<>("aircraftId");
    public static final PropertyPath<ZonedDateTime> TIMESTAMP = new PropertyPath<>("timestamp");
    public static final PropertyPath<Integer> NUMBER_OF_ECONOMY_SEATS = new PropertyPath<>("numberOfEconomySeats");
    public static final PropertyPath<BigDecimal> PRICE_OF_ECONOMY_SEAT = new PropertyPath<>("priceOfEconomySeat");
    public static final PropertyPath<Integer> NUMBER_OF_BUSINESS_SEATS = new PropertyPath<>("numberOfBusinessSeats");
    public static final PropertyPath<BigDecimal> PRICE_OF_BUSINESS_SEATS = new PropertyPath<>("priceOfBusinessSeats");
    public static final PropertyPath<String> FROM_AIRPORT = new PropertyPath<>("fromAirport");
    public static final PropertyPath<String> TO_AIRPORT = new PropertyPath<>("toAirport");
    public static final PropertyPath<String> AIRCRAFT_MAKER = new PropertyPath<>("aircraftMaker");
    public static final PropertyPath<String> AIRCRAFT_TYPE = new PropertyPath<>("aircraftType");
    public static final PropertyPath<Long> AIRCRAFT_AIRLINE_ID = new PropertyPath<>("aircraftAirlineId");
    public static final PropertyPath<Long> AIRLINE_ID = new PropertyPath<>("airlineId");
    public static final PropertyPath<String> AIRLINE_NAME = new PropertyPath<>("airlineName");
    public static final PropertyPath<String> AIRLINE_DESCRIPTION = new PropertyPath<>("airlineDescription");
    public static final PropertyPath<String> AIRLINE_LUGGAGE_DETAILS = new PropertyPath<>("airlineLuggageDetails");

    @NotNull
    private Long id;

    @NotNull
    private Long aircraftId;

    @NotNull
    private ZonedDateTime timestamp;

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

    @NotNull
    @Size(min = 1, max = 128)
    private String aircraftMaker;

    @NotNull
    @Size(min = 1, max = 128)
    private String aircraftType;

    @NotNull
    private Long aircraftAirlineId;

    @NotNull
    private Long airlineId;

    @NotNull
    @Size(min = 1, max = 128)
    private String airlineName;

    @NotNull
    @Size(max = 256)
    private String airlineDescription;

    @Size(max = 256)
    private String airlineLuggageDetails;

    private FlightsResponse() {
    }

    public FlightsResponse(Long id, Long aircraftId, ZonedDateTime timestamp, Integer numberOfEconomySeats, BigDecimal priceOfEconomySeat, Integer numberOfBusinessSeats,
            BigDecimal priceOfBusinessSeats, String fromAirport, String toAirport, String aircraftMaker, String aircraftType, Long aircraftAirlineId, Long airlineId, String airlineName,
            String airlineDescription, String airlineLuggageDetails) {
        this.id = id;
        this.aircraftId = aircraftId;
        this.timestamp = timestamp;
        this.numberOfEconomySeats = numberOfEconomySeats;
        this.priceOfEconomySeat = priceOfEconomySeat;
        this.numberOfBusinessSeats = numberOfBusinessSeats;
        this.priceOfBusinessSeats = priceOfBusinessSeats;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.aircraftMaker = aircraftMaker;
        this.aircraftType = aircraftType;
        this.aircraftAirlineId = aircraftAirlineId;
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.airlineDescription = airlineDescription;
        this.airlineLuggageDetails = airlineLuggageDetails;
    }

    public Long getId() {
        return id;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
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

    public String getAircraftMaker() {
        return aircraftMaker;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public Long getAircraftAirlineId() {
        return aircraftAirlineId;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getAirlineDescription() {
        return airlineDescription;
    }

    public String getAirlineLuggageDetails() {
        return airlineLuggageDetails;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FlightsResponse other = (FlightsResponse) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.aircraftId != null && other.aircraftId != null && !this.aircraftId.equals(other.aircraftId))
            return false;
        if (this.timestamp != null && other.timestamp != null && !this.timestamp.equals(other.timestamp))
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
        if (this.aircraftMaker != null && other.aircraftMaker != null && !this.aircraftMaker.equals(other.aircraftMaker))
            return false;
        if (this.aircraftType != null && other.aircraftType != null && !this.aircraftType.equals(other.aircraftType))
            return false;
        if (this.aircraftAirlineId != null && other.aircraftAirlineId != null && !this.aircraftAirlineId.equals(other.aircraftAirlineId))
            return false;
        if (this.airlineId != null && other.airlineId != null && !this.airlineId.equals(other.airlineId))
            return false;
        if (this.airlineName != null && other.airlineName != null && !this.airlineName.equals(other.airlineName))
            return false;
        if (this.airlineDescription != null && other.airlineDescription != null && !this.airlineDescription.equals(other.airlineDescription))
            return false;
        if (this.airlineLuggageDetails != null && other.airlineLuggageDetails != null && !this.airlineLuggageDetails.equals(other.airlineLuggageDetails))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.aircraftId == null) ? 0 : this.aircraftId.hashCode());
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.numberOfEconomySeats == null) ? 0 : this.numberOfEconomySeats.hashCode());
        result = prime * result + ((this.priceOfEconomySeat == null) ? 0 : this.priceOfEconomySeat.hashCode());
        result = prime * result + ((this.numberOfBusinessSeats == null) ? 0 : this.numberOfBusinessSeats.hashCode());
        result = prime * result + ((this.priceOfBusinessSeats == null) ? 0 : this.priceOfBusinessSeats.hashCode());
        result = prime * result + ((this.fromAirport == null) ? 0 : this.fromAirport.hashCode());
        result = prime * result + ((this.toAirport == null) ? 0 : this.toAirport.hashCode());
        result = prime * result + ((this.aircraftMaker == null) ? 0 : this.aircraftMaker.hashCode());
        result = prime * result + ((this.aircraftType == null) ? 0 : this.aircraftType.hashCode());
        result = prime * result + ((this.aircraftAirlineId == null) ? 0 : this.aircraftAirlineId.hashCode());
        result = prime * result + ((this.airlineId == null) ? 0 : this.airlineId.hashCode());
        result = prime * result + ((this.airlineName == null) ? 0 : this.airlineName.hashCode());
        result = prime * result + ((this.airlineDescription == null) ? 0 : this.airlineDescription.hashCode());
        result = prime * result + ((this.airlineLuggageDetails == null) ? 0 : this.airlineLuggageDetails.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FlightsResponse[" + "this.id=" + this.id + ", this.aircraftId=" + this.aircraftId + ", this.timestamp=" + this.timestamp + ", this.numberOfEconomySeats=" + this.numberOfEconomySeats
                + ", this.priceOfEconomySeat=" + this.priceOfEconomySeat + ", this.numberOfBusinessSeats=" + this.numberOfBusinessSeats + ", this.priceOfBusinessSeats=" + this.priceOfBusinessSeats
                + ", this.fromAirport=" + this.fromAirport + ", this.toAirport=" + this.toAirport + ", this.aircraftMaker=" + this.aircraftMaker + ", this.aircraftType=" + this.aircraftType
                + ", this.aircraftAirlineId=" + this.aircraftAirlineId + ", this.airlineId=" + this.airlineId + ", this.airlineName=" + this.airlineName + ", this.airlineDescription="
                + this.airlineDescription + ", this.airlineLuggageDetails=" + this.airlineLuggageDetails + "]";
    }

}
