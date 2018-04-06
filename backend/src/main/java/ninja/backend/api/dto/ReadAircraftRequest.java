package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class ReadAircraftRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");

    @NotNull
    private Long id;

    private ReadAircraftRequest() {
    }

    public ReadAircraftRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ReadAircraftRequest other = (ReadAircraftRequest) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ReadAircraftRequest[" + "this.id=" + this.id + "]";
    }

}
