package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class CreateAirlineRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> NAME = new PropertyPath<>("name");
    public static final PropertyPath<String> DESCRIPTION = new PropertyPath<>("description");
    public static final PropertyPath<String> LUGGAGE_DETAILS = new PropertyPath<>("luggageDetails");

    @NotNull
    @Size(min = 1, max = 128)
    private String name;

    @NotNull
    @Size(max = 256)
    private String description;

    @Size(max = 256)
    private String luggageDetails;

    private CreateAirlineRequest() {
    }

    public CreateAirlineRequest(String name, String description, String luggageDetails) {
        this.name = name;
        this.description = description;
        this.luggageDetails = luggageDetails;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLuggageDetails() {
        return luggageDetails;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CreateAirlineRequest other = (CreateAirlineRequest) obj;
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
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.luggageDetails == null) ? 0 : this.luggageDetails.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CreateAirlineRequest[" + "this.name=" + this.name + ", this.description=" + this.description + ", this.luggageDetails=" + this.luggageDetails + "]";
    }

}
