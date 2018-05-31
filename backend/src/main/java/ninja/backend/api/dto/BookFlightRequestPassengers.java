package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import ninja.backend.model.enumeration.*;
import eu.execom.fabut.property.PropertyPath;


public class BookFlightRequestPassengers implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> FIRST_NAME = new PropertyPath<>("firstName");
    public static final PropertyPath<String> LAST_NAME = new PropertyPath<>("lastName");
    public static final PropertyPath<String> PASSPORT_NUMBER = new PropertyPath<>("passportNumber");
    public static final PropertyPath<Boolean> SMALL_CABIN_LUGGAGE = new PropertyPath<>("smallCabinLuggage");
    public static final PropertyPath<Boolean> LARGE_CABIN_LUGGAGE = new PropertyPath<>("largeCabinLuggage");
    public static final PropertyPath<Boolean> CHECKED_BAG = new PropertyPath<>("checkedBag");
    public static final PropertyPath<SeatType> SEAT_TYPE = new PropertyPath<>("seatType");

    @NotNull
    @Size(min = 1, max = 100)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 20)
    private String passportNumber;

    @NotNull
    private Boolean smallCabinLuggage;

    @NotNull
    private Boolean largeCabinLuggage;

    @NotNull
    private Boolean checkedBag;

    @NotNull
    private SeatType seatType;

    private BookFlightRequestPassengers() {
    }

    public BookFlightRequestPassengers(String firstName, String lastName, String passportNumber, Boolean smallCabinLuggage, Boolean largeCabinLuggage, Boolean checkedBag, SeatType seatType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.smallCabinLuggage = smallCabinLuggage;
        this.largeCabinLuggage = largeCabinLuggage;
        this.checkedBag = checkedBag;
        this.seatType = seatType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Boolean getSmallCabinLuggage() {
        return smallCabinLuggage;
    }

    public Boolean getLargeCabinLuggage() {
        return largeCabinLuggage;
    }

    public Boolean getCheckedBag() {
        return checkedBag;
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
        final BookFlightRequestPassengers other = (BookFlightRequestPassengers) obj;
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
        return "BookFlightRequestPassengers[" + "this.firstName=" + this.firstName + ", this.lastName=" + this.lastName + ", this.passportNumber=" + this.passportNumber + ", this.smallCabinLuggage="
                + this.smallCabinLuggage + ", this.largeCabinLuggage=" + this.largeCabinLuggage + ", this.checkedBag=" + this.checkedBag + ", this.seatType=" + this.seatType + "]";
    }

}
