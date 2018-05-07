package ninja.backend.model;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "Airline")
public class Airline implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<String> NAME = new PropertyPath<>("name");
    public static final PropertyPath<String> DESCRIPTION = new PropertyPath<>("description");
    public static final PropertyPath<Optional<String>> LUGGAGE_DETAILS = new PropertyPath<>("luggageDetails");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 256)
    @Column(name = "description")
    private String description;

    @Size(max = 256)
    @Column(name = "luggageDetails")
    private String luggageDetails;

    @NotNull
    @OneToMany(mappedBy = "airline", cascade = CascadeType.PERSIST)
    private final Set<Aircraft> aircrafts = new LinkedHashSet<>();

    public Airline() {
    }

    public Airline(Long id, String name, String description, Optional<String> luggageDetails) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.luggageDetails = luggageDetails.orElse(null);
    }

    public Airline(String name, String description, Optional<String> luggageDetails) {
        this.name = name;
        this.description = description;
        this.luggageDetails = luggageDetails.orElse(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<String> getLuggageDetails() {
        return Optional.ofNullable(luggageDetails);
    }

    public void setLuggageDetails(Optional<String> luggageDetails) {
        this.luggageDetails = luggageDetails.orElse(null);
    }

    public Set<Aircraft> getAircrafts() {
        return aircrafts;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Airline other = (Airline) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.name != null && other.name != null && !this.name.equals(other.name))
            return false;
        if (this.description != null && other.description != null && !this.description.equals(other.description))
            return false;
        if (this.luggageDetails != null && other.luggageDetails != null && !this.luggageDetails.equals(other.luggageDetails))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.luggageDetails == null) ? 0 : this.luggageDetails.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Airline[" + "this.id=" + this.id + ", this.name=" + this.name + ", this.description=" + this.description + ", this.luggageDetails=" + this.luggageDetails + "]";
    }

}
