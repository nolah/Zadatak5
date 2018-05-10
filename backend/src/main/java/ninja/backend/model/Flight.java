package ninja.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "Flight")
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<Aircraft> AIRCRAFT = new PropertyPath<>("aircraft");
    public static final PropertyPath<ZonedDateTime> TIMESTAMP = new PropertyPath<>("timestamp");
    public static final PropertyPath<Integer> NUMBER_OF_ECONOMY_SEATS = new PropertyPath<>("numberOfEconomySeats");
    public static final PropertyPath<BigDecimal> PRICE_OF_ECONOMY_SEAT = new PropertyPath<>("priceOfEconomySeat");
    public static final PropertyPath<Integer> NUMBER_OF_BUSINESS_SEATS = new PropertyPath<>("numberOfBusinessSeats");
    public static final PropertyPath<BigDecimal> PRICE_OF_BUSINESS_SEATS = new PropertyPath<>("priceOfBusinessSeats");
    public static final PropertyPath<String> FROM_AIRPORT = new PropertyPath<>("fromAirport");
    public static final PropertyPath<String> TO_AIRPORT = new PropertyPath<>("toAirport");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "aircraftId")
    private Aircraft aircraft;

    @NotNull
    @Column(name = "timestamp")
    private ZonedDateTime timestamp;

    @NotNull
    @Column(name = "numberOfEconomySeats")
    private Integer numberOfEconomySeats;

    @NotNull
    @Column(name = "priceOfEconomySeat")
    private BigDecimal priceOfEconomySeat;

    @NotNull
    @Column(name = "numberOfBusinessSeats")
    private Integer numberOfBusinessSeats;

    @NotNull
    @Column(name = "priceOfBusinessSeats")
    private BigDecimal priceOfBusinessSeats;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "fromAirport")
    private String fromAirport;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "toAirport")
    private String toAirport;

    public Flight() {
    }

    public Flight(Long id, Aircraft aircraft, ZonedDateTime timestamp, Integer numberOfEconomySeats, BigDecimal priceOfEconomySeat, Integer numberOfBusinessSeats, BigDecimal priceOfBusinessSeats,
            String fromAirport, String toAirport) {
        this.id = id;
        this.aircraft = aircraft;
        this.timestamp = timestamp;
        this.numberOfEconomySeats = numberOfEconomySeats;
        this.priceOfEconomySeat = priceOfEconomySeat;
        this.numberOfBusinessSeats = numberOfBusinessSeats;
        this.priceOfBusinessSeats = priceOfBusinessSeats;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
    }

    public Flight(Aircraft aircraft, ZonedDateTime timestamp, Integer numberOfEconomySeats, BigDecimal priceOfEconomySeat, Integer numberOfBusinessSeats, BigDecimal priceOfBusinessSeats,
            String fromAirport, String toAirport) {
        this.aircraft = aircraft;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getNumberOfEconomySeats() {
        return numberOfEconomySeats;
    }

    public void setNumberOfEconomySeats(Integer numberOfEconomySeats) {
        this.numberOfEconomySeats = numberOfEconomySeats;
    }

    public BigDecimal getPriceOfEconomySeat() {
        return priceOfEconomySeat;
    }

    public void setPriceOfEconomySeat(BigDecimal priceOfEconomySeat) {
        this.priceOfEconomySeat = priceOfEconomySeat;
    }

    public Integer getNumberOfBusinessSeats() {
        return numberOfBusinessSeats;
    }

    public void setNumberOfBusinessSeats(Integer numberOfBusinessSeats) {
        this.numberOfBusinessSeats = numberOfBusinessSeats;
    }

    public BigDecimal getPriceOfBusinessSeats() {
        return priceOfBusinessSeats;
    }

    public void setPriceOfBusinessSeats(BigDecimal priceOfBusinessSeats) {
        this.priceOfBusinessSeats = priceOfBusinessSeats;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Flight other = (Flight) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (!((this.aircraft == null && other.aircraft == null) || (this.aircraft != null && other.aircraft != null && this.aircraft.getId() == null && other.aircraft.getId() == null)
                || this.aircraft.getId().equals(other.aircraft.getId())))
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
        result = prime * result + ((this.aircraft == null || this.aircraft.getId() == null) ? 0 : this.aircraft.getId().hashCode());
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
        return "Flight[" + "this.id=" + this.id + ", this.aircraft=" + (this.aircraft == null ? this.aircraft : this.aircraft.getId()) + ", this.timestamp=" + this.timestamp
                + ", this.numberOfEconomySeats=" + this.numberOfEconomySeats + ", this.priceOfEconomySeat=" + this.priceOfEconomySeat + ", this.numberOfBusinessSeats=" + this.numberOfBusinessSeats
                + ", this.priceOfBusinessSeats=" + this.priceOfBusinessSeats + ", this.fromAirport=" + this.fromAirport + ", this.toAirport=" + this.toAirport + "]";
    }

}
