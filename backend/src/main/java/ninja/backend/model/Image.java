package ninja.backend.model;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "Image")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<File> FILE = new PropertyPath<>("file");
    public static final PropertyPath<Aircraft> AIRCRAFT = new PropertyPath<>("aircraft");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "file")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "path", column = @Column(name = "filePath"))})
    private File file;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "aircraftId")
    private Aircraft aircraft;

    public Image() {
    }

    public Image(Long id, File file, Aircraft aircraft) {
        this.id = id;
        this.file = file;
        this.aircraft = aircraft;
    }

    public Image(File file, Aircraft aircraft) {
        this.file = file;
        this.aircraft = aircraft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Image other = (Image) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.file != null && other.file != null && !this.file.equals(other.file))
            return false;
        if (!((this.aircraft == null && other.aircraft == null) || (this.aircraft != null && other.aircraft != null && this.aircraft.getId() == null && other.aircraft.getId() == null)
                || this.aircraft.getId().equals(other.aircraft.getId())))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
        result = prime * result + ((this.aircraft == null || this.aircraft.getId() == null) ? 0 : this.aircraft.getId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Image[" + "this.id=" + this.id + ", this.file=" + this.file + ", this.aircraft=" + (this.aircraft == null ? this.aircraft : this.aircraft.getId()) + "]";
    }

}
