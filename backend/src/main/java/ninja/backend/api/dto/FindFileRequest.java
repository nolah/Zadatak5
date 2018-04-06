package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class FindFileRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> KEY = new PropertyPath<>("key");
    public static final PropertyPath<String> FILE_NAME = new PropertyPath<>("fileName");

    @NotNull
    @Size(max = 255)
    private String key;

    @NotNull
    @Size(max = 255)
    private String fileName;

    private FindFileRequest() {
    }

    public FindFileRequest(String key, String fileName) {
        this.key = key;
        this.fileName = fileName;
    }

    public String getKey() {
        return key;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FindFileRequest other = (FindFileRequest) obj;
        if (this.key != null && other.key != null && !this.key.equals(other.key))
            return false;
        if (this.fileName != null && other.fileName != null && !this.fileName.equals(other.fileName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.key == null) ? 0 : this.key.hashCode());
        result = prime * result + ((this.fileName == null) ? 0 : this.fileName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FindFileRequest[" + "this.key=" + this.key + ", this.fileName=" + this.fileName + "]";
    }

}
