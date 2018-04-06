package ninja.backend.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;


@Embeddable
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 255)
    private String path;

    public File() {
    }

    public File(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final File other = (File) obj;
        if (this.path != null && other.path != null && !this.path.equals(other.path))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.path == null) ? 0 : this.path.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "File[" + "this.path=" + this.path + "]";
    }

}
