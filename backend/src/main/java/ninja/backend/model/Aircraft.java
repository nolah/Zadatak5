package ninja.backend.model;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "Aircraft")
public class Aircraft implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<String> MAKER = new PropertyPath<>("maker");
    public static final PropertyPath<String> TYPE = new PropertyPath<>("type");
    public static final PropertyPath<Airline> AIRLINE = new PropertyPath<>("airline");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "maker")
    private String maker;

    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "type")
    private String type;

    @NotNull
    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.PERSIST)
    private final Set<Image> images = new LinkedHashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "airlineId")
    private Airline airline;

    public Aircraft() {
    }

    public Aircraft(Long id, String maker, String type, Airline airline) {
        this.id = id;
        this.maker = maker;
        this.type = type;
        this.airline = airline;
    }

    public Aircraft(String maker, String type, Airline airline) {
        this.maker = maker;
        this.type = type;
        this.airline = airline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Aircraft other = (Aircraft) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.maker != null && other.maker != null && !this.maker.equals(other.maker))
            return false;
        if (this.type != null && other.type != null && !this.type.equals(other.type))
            return false;
        if (!((this.airline == null && other.airline == null) || (this.airline != null && other.airline != null && this.airline.getId() == null && other.airline.getId() == null)
                || this.airline.getId().equals(other.airline.getId())))
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
        result = prime * result + ((this.airline == null || this.airline.getId() == null) ? 0 : this.airline.getId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Aircraft[" + "this.id=" + this.id + ", this.maker=" + this.maker + ", this.type=" + this.type + ", this.airline=" + (this.airline == null ? this.airline : this.airline.getId()) + "]";
    }

}
