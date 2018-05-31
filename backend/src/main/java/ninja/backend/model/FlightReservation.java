package ninja.backend.model;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "FlightReservation")
public class FlightReservation implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<Optional<Flight>> DEPARTING_FLIGHT = new PropertyPath<>("departingFlight");
    public static final PropertyPath<Optional<Flight>> RETURNING_FLIGHT = new PropertyPath<>("returningFlight");
    public static final PropertyPath<User> USER = new PropertyPath<>("user");
    public static final PropertyPath<PaymentMethod> PAYMENT_METHOD = new PropertyPath<>("paymentMethod");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departingFlightId")
    private Flight departingFlight;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "returningFlightId")
    private Flight returningFlight;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMethod")
    private PaymentMethod paymentMethod;

    @NotNull
    @OneToMany(mappedBy = "flightReservation", cascade = CascadeType.PERSIST)
    private final Set<FlightPassenger> flightPassengers = new LinkedHashSet<>();

    public FlightReservation() {
    }

    public FlightReservation(Long id, Optional<Flight> departingFlight, Optional<Flight> returningFlight, User user, PaymentMethod paymentMethod) {
        this.id = id;
        this.departingFlight = departingFlight.orElse(null);
        this.returningFlight = returningFlight.orElse(null);
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public FlightReservation(Optional<Flight> departingFlight, Optional<Flight> returningFlight, User user, PaymentMethod paymentMethod) {
        this.departingFlight = departingFlight.orElse(null);
        this.returningFlight = returningFlight.orElse(null);
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<Flight> getDepartingFlight() {
        return Optional.ofNullable(departingFlight);
    }

    public void setDepartingFlight(Optional<Flight> departingFlight) {
        this.departingFlight = departingFlight.orElse(null);
    }

    public Optional<Flight> getReturningFlight() {
        return Optional.ofNullable(returningFlight);
    }

    public void setReturningFlight(Optional<Flight> returningFlight) {
        this.returningFlight = returningFlight.orElse(null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Set<FlightPassenger> getFlightPassengers() {
        return flightPassengers;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FlightReservation other = (FlightReservation) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (!((this.departingFlight == null && other.departingFlight == null)
                || (this.departingFlight != null && other.departingFlight != null && this.departingFlight.getId() == null && other.departingFlight.getId() == null)
                || this.departingFlight.getId().equals(other.departingFlight.getId())))
            return false;

        if (!((this.returningFlight == null && other.returningFlight == null)
                || (this.returningFlight != null && other.returningFlight != null && this.returningFlight.getId() == null && other.returningFlight.getId() == null)
                || this.returningFlight.getId().equals(other.returningFlight.getId())))
            return false;

        if (!((this.user == null && other.user == null) || (this.user != null && other.user != null && this.user.getId() == null && other.user.getId() == null)
                || this.user.getId().equals(other.user.getId())))
            return false;

        if (this.paymentMethod != null && other.paymentMethod != null && !this.paymentMethod.equals(other.paymentMethod))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.departingFlight == null || this.departingFlight.getId() == null) ? 0 : this.departingFlight.getId().hashCode());
        result = prime * result + ((this.returningFlight == null || this.returningFlight.getId() == null) ? 0 : this.returningFlight.getId().hashCode());
        result = prime * result + ((this.user == null || this.user.getId() == null) ? 0 : this.user.getId().hashCode());
        result = prime * result + ((this.paymentMethod == null) ? 0 : this.paymentMethod.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FlightReservation[" + "this.id=" + this.id + ", this.departingFlight=" + (this.departingFlight == null ? this.departingFlight : this.departingFlight.getId())
                + ", this.returningFlight=" + (this.returningFlight == null ? this.returningFlight : this.returningFlight.getId()) + ", this.user="
                + (this.user == null ? this.user : this.user.getId()) + ", this.paymentMethod=" + this.paymentMethod + "]";
    }

}
