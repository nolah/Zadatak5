package ninja.backend.model;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "FlightPassenger")
public class FlightPassenger implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<FlightReservation> FLIGHT_RESERVATION = new PropertyPath<>("flightReservation");
    public static final PropertyPath<String> FIRST_NAME = new PropertyPath<>("firstName");
    public static final PropertyPath<String> LAST_NAME = new PropertyPath<>("lastName");
    public static final PropertyPath<String> PASSPORT_NUMBER = new PropertyPath<>("passportNumber");
    public static final PropertyPath<Boolean> SMALL_CABIN_LUGGAGE = new PropertyPath<>("smallCabinLuggage");
    public static final PropertyPath<Boolean> LARGE_CABIN_LUGGAGE = new PropertyPath<>("largeCabinLuggage");
    public static final PropertyPath<Boolean> CHECKED_BAG = new PropertyPath<>("checkedBag");
    public static final PropertyPath<SeatType> SEAT_TYPE = new PropertyPath<>("seatType");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "flightReservationId")
    private FlightReservation flightReservation;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lastName")
    private String lastName;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "passportNumber")
    private String passportNumber;

    @NotNull
    @Column(name = "smallCabinLuggage")
    private Boolean smallCabinLuggage;

    @NotNull
    @Column(name = "largeCabinLuggage")
    private Boolean largeCabinLuggage;

    @NotNull
    @Column(name = "checkedBag")
    private Boolean checkedBag;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "seatType")
    private SeatType seatType;

    public FlightPassenger() {
    }

    public FlightPassenger(Long id, FlightReservation flightReservation, String firstName, String lastName, String passportNumber, Boolean smallCabinLuggage, Boolean largeCabinLuggage,
            Boolean checkedBag, SeatType seatType) {
        this.id = id;
        this.flightReservation = flightReservation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.smallCabinLuggage = smallCabinLuggage;
        this.largeCabinLuggage = largeCabinLuggage;
        this.checkedBag = checkedBag;
        this.seatType = seatType;
    }

    public FlightPassenger(FlightReservation flightReservation, String firstName, String lastName, String passportNumber, Boolean smallCabinLuggage, Boolean largeCabinLuggage, Boolean checkedBag,
            SeatType seatType) {
        this.flightReservation = flightReservation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.smallCabinLuggage = smallCabinLuggage;
        this.largeCabinLuggage = largeCabinLuggage;
        this.checkedBag = checkedBag;
        this.seatType = seatType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Boolean getSmallCabinLuggage() {
        return smallCabinLuggage;
    }

    public void setSmallCabinLuggage(Boolean smallCabinLuggage) {
        this.smallCabinLuggage = smallCabinLuggage;
    }

    public Boolean getLargeCabinLuggage() {
        return largeCabinLuggage;
    }

    public void setLargeCabinLuggage(Boolean largeCabinLuggage) {
        this.largeCabinLuggage = largeCabinLuggage;
    }

    public Boolean getCheckedBag() {
        return checkedBag;
    }

    public void setCheckedBag(Boolean checkedBag) {
        this.checkedBag = checkedBag;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FlightPassenger other = (FlightPassenger) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (!((this.flightReservation == null && other.flightReservation == null)
                || (this.flightReservation != null && other.flightReservation != null && this.flightReservation.getId() == null && other.flightReservation.getId() == null)
                || this.flightReservation.getId().equals(other.flightReservation.getId())))
            return false;

        if (this.firstName != null && other.firstName != null && !this.firstName.equals(other.firstName))
            return false;
        if (this.lastName != null && other.lastName != null && !this.lastName.equals(other.lastName))
            return false;
        if (this.passportNumber != null && other.passportNumber != null && !this.passportNumber.equals(other.passportNumber))
            return false;
        if (this.smallCabinLuggage != null && other.smallCabinLuggage != null && !this.smallCabinLuggage.equals(other.smallCabinLuggage))
            return false;
        if (this.largeCabinLuggage != null && other.largeCabinLuggage != null && !this.largeCabinLuggage.equals(other.largeCabinLuggage))
            return false;
        if (this.checkedBag != null && other.checkedBag != null && !this.checkedBag.equals(other.checkedBag))
            return false;
        if (this.seatType != null && other.seatType != null && !this.seatType.equals(other.seatType))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.flightReservation == null || this.flightReservation.getId() == null) ? 0 : this.flightReservation.getId().hashCode());
        result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
        result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
        result = prime * result + ((this.passportNumber == null) ? 0 : this.passportNumber.hashCode());
        result = prime * result + ((this.smallCabinLuggage == null) ? 0 : this.smallCabinLuggage.hashCode());
        result = prime * result + ((this.largeCabinLuggage == null) ? 0 : this.largeCabinLuggage.hashCode());
        result = prime * result + ((this.checkedBag == null) ? 0 : this.checkedBag.hashCode());
        result = prime * result + ((this.seatType == null) ? 0 : this.seatType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FlightPassenger[" + "this.id=" + this.id + ", this.flightReservation=" + (this.flightReservation == null ? this.flightReservation : this.flightReservation.getId())
                + ", this.firstName=" + this.firstName + ", this.lastName=" + this.lastName + ", this.passportNumber=" + this.passportNumber + ", this.smallCabinLuggage=" + this.smallCabinLuggage
                + ", this.largeCabinLuggage=" + this.largeCabinLuggage + ", this.checkedBag=" + this.checkedBag + ", this.seatType=" + this.seatType + "]";
    }

}
