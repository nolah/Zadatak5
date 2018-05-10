package ninja.backend.api.dto;

import java.io.Serializable;

import java.math.BigDecimal;
import java.time.*;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class UpdateFlightRequest implements Serializable {

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

    private UpdateFlightRequest() {
    }

    public UpdateFlightRequest(Long id, Long aircraftId, ZonedDateTime timestamp, Integer numberOfEconomySeats, BigDecimal priceOfEconomySeat, Integer numberOfBusinessSeats,
            BigDecimal priceOfBusinessSeats, String fromAirport, String toAirport) {
        this.id = id;
        this.aircraftId = aircraftId;
        this.timestamp = timestamp;
        this.numberOfEconomySeats = numberOfEconomySeats;
        this.priceOfEconomySeat = priceOfEconomySeat;
        this.numberOfBusinessSeats = numberOfBusinessSeats;
        this.priceOfBusinessSeats = priceOfBusinessSeats;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UpdateFlightRequest other = (UpdateFlightRequest) obj;
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
        return result;
    }

    @Override
    public String toString() {
        return "UpdateFlightRequest[" + "this.id=" + this.id + ", this.aircraftId=" + this.aircraftId + ", this.timestamp=" + this.timestamp + ", this.numberOfEconomySeats="
                + this.numberOfEconomySeats + ", this.priceOfEconomySeat=" + this.priceOfEconomySeat + ", this.numberOfBusinessSeats=" + this.numberOfBusinessSeats + ", this.priceOfBusinessSeats="
                + this.priceOfBusinessSeats + ", this.fromAirport=" + this.fromAirport + ", this.toAirport=" + this.toAirport + "]";
    }

}
