package ninja.backend.api.dto;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.*;

import ninja.backend.model.enumeration.*;
import eu.execom.fabut.property.PropertyPath;


public class BookFlightRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> DEPARTING_FLIGHT = new PropertyPath<>("departingFlight");
    public static final PropertyPath<Long> RETURNING_FLIGHT = new PropertyPath<>("returningFlight");
    public static final PropertyPath<PaymentMethod> PAYMENT_METHOD = new PropertyPath<>("paymentMethod");
    public static final PropertyPath<List<BookFlightRequestPassengers>> PASSENGERS = new PropertyPath<>("passengers");

    private Long departingFlight;

    private Long returningFlight;

    @NotNull
    private PaymentMethod paymentMethod;

    @Valid
    private List<BookFlightRequestPassengers> passengers = new ArrayList<>();

    private BookFlightRequest() {
    }

    public BookFlightRequest(Long departingFlight, Long returningFlight, PaymentMethod paymentMethod, List<BookFlightRequestPassengers> passengers) {
        this.departingFlight = departingFlight;
        this.returningFlight = returningFlight;
        this.paymentMethod = paymentMethod;
        this.passengers = passengers;
    }

    public Long getDepartingFlight() {
        return departingFlight;
    }

    public Long getReturningFlight() {
        return returningFlight;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public List<BookFlightRequestPassengers> getPassengers() {
        return passengers;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final BookFlightRequest other = (BookFlightRequest) obj;
        if (this.departingFlight != null && other.departingFlight != null && !this.departingFlight.equals(other.departingFlight))
            return false;
        if (this.returningFlight != null && other.returningFlight != null && !this.returningFlight.equals(other.returningFlight))
            return false;
        if (this.paymentMethod != null && other.paymentMethod != null && !this.paymentMethod.equals(other.paymentMethod))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.departingFlight == null) ? 0 : this.departingFlight.hashCode());
        result = prime * result + ((this.returningFlight == null) ? 0 : this.returningFlight.hashCode());
        result = prime * result + ((this.paymentMethod == null) ? 0 : this.paymentMethod.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BookFlightRequest[" + "this.departingFlight=" + this.departingFlight + ", this.returningFlight=" + this.returningFlight + ", this.paymentMethod=" + this.paymentMethod
                + ", this.passengers=" + this.passengers + "]";
    }

}
