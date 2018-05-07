package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class AircraftsResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<String> MAKER = new PropertyPath<>("maker");
    public static final PropertyPath<String> TYPE = new PropertyPath<>("type");
    public static final PropertyPath<Long> AIRLINE_ID = new PropertyPath<>("airlineId");
    public static final PropertyPath<String> AIRLINE_NAME = new PropertyPath<>("airlineName");
    public static final PropertyPath<String> AIRLINE_DESCRIPTION = new PropertyPath<>("airlineDescription");
    public static final PropertyPath<String> AIRLINE_LUGGAGE_DETAILS = new PropertyPath<>("airlineLuggageDetails");

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 128)
    private String maker;

    @NotNull
    @Size(min = 1, max = 128)
    private String type;

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

    private AircraftsResponse() {
    }

    public AircraftsResponse(Long id, String maker, String type, Long airlineId, String airlineName, String airlineDescription, String airlineLuggageDetails) {
        this.id = id;
        this.maker = maker;
        this.type = type;
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.airlineDescription = airlineDescription;
        this.airlineLuggageDetails = airlineLuggageDetails;
    }

    public Long getId() {
        return id;
    }

    public String getMaker() {
        return maker;
    }

    public String getType() {
        return type;
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
        final AircraftsResponse other = (AircraftsResponse) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.maker != null && other.maker != null && !this.maker.equals(other.maker))
            return false;
        if (this.type != null && other.type != null && !this.type.equals(other.type))
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
        result = prime * result + ((this.maker == null) ? 0 : this.maker.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.airlineId == null) ? 0 : this.airlineId.hashCode());
        result = prime * result + ((this.airlineName == null) ? 0 : this.airlineName.hashCode());
        result = prime * result + ((this.airlineDescription == null) ? 0 : this.airlineDescription.hashCode());
        result = prime * result + ((this.airlineLuggageDetails == null) ? 0 : this.airlineLuggageDetails.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "AircraftsResponse[" + "this.id=" + this.id + ", this.maker=" + this.maker + ", this.type=" + this.type + ", this.airlineId=" + this.airlineId + ", this.airlineName=" + this.airlineName
                + ", this.airlineDescription=" + this.airlineDescription + ", this.airlineLuggageDetails=" + this.airlineLuggageDetails + "]";
    }

}
