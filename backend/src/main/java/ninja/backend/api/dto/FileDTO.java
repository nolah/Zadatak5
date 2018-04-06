package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class FileDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> PATH = new PropertyPath<>("path");

    @NotNull
    @Size(max = 255)
    private String path;

    private FileDTO() {
    }

    public FileDTO(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FileDTO other = (FileDTO) obj;
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
        return "FileDTO[" + "this.path=" + this.path + "]";
    }

}
