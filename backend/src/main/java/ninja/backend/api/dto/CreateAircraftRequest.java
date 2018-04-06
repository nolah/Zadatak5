package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class CreateAircraftRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> MAKER = new PropertyPath<>("maker");
    public static final PropertyPath<String> TYPE = new PropertyPath<>("type");

    @NotNull
    @Size(min = 1, max = 128)
    private String maker;

    @NotNull
    @Size(min = 1, max = 128)
    private String type;

    private CreateAircraftRequest() {
    }

    public CreateAircraftRequest(String maker, String type) {
        this.maker = maker;
        this.type = type;
    }

    public String getMaker() {
        return maker;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CreateAircraftRequest other = (CreateAircraftRequest) obj;
        if (this.maker != null && other.maker != null && !this.maker.equals(other.maker))
            return false;
        if (this.type != null && other.type != null && !this.type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.maker == null) ? 0 : this.maker.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CreateAircraftRequest[" + "this.maker=" + this.maker + ", this.type=" + this.type + "]";
    }

}
