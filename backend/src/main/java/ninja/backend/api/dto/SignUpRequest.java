package ninja.backend.api.dto;

import java.io.Serializable;

import java.time.*;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class SignUpRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> SET_PASSWORD_CODE = new PropertyPath<>("setPasswordCode");
    public static final PropertyPath<ZonedDateTime> SET_PASSWORD_TIMESTAMP = new PropertyPath<>("setPasswordTimestamp");
    public static final PropertyPath<String> USERNAME = new PropertyPath<>("username");
    public static final PropertyPath<String> PASSWORD = new PropertyPath<>("password");

    @Size(max = 255)
    private String setPasswordCode;

    @NotNull
    private ZonedDateTime setPasswordTimestamp;

    @NotNull
    @Size(min = 3, max = 128)
    private String username;

    @NotNull
    @Size(min = 6, max = 32)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*&@%+/_'!#$^?:.(\\)\\[\\]{}~\\-]{8,}$")
    private String password;

    private SignUpRequest() {
    }

    public SignUpRequest(String setPasswordCode, ZonedDateTime setPasswordTimestamp, String username, String password) {
        this.setPasswordCode = setPasswordCode;
        this.setPasswordTimestamp = setPasswordTimestamp;
        this.username = username;
        this.password = password;
    }

    public String getSetPasswordCode() {
        return setPasswordCode;
    }

    public ZonedDateTime getSetPasswordTimestamp() {
        return setPasswordTimestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SignUpRequest other = (SignUpRequest) obj;
        if (this.setPasswordCode != null && other.setPasswordCode != null && !this.setPasswordCode.equals(other.setPasswordCode))
            return false;
        if (this.setPasswordTimestamp != null && other.setPasswordTimestamp != null && !this.setPasswordTimestamp.equals(other.setPasswordTimestamp))
            return false;
        if (this.username != null && other.username != null && !this.username.equals(other.username))
            return false;
        if (this.password != null && other.password != null && !this.password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.setPasswordCode == null) ? 0 : this.setPasswordCode.hashCode());
        result = prime * result + ((this.setPasswordTimestamp == null) ? 0 : this.setPasswordTimestamp.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SignUpRequest[" + "this.setPasswordTimestamp=" + this.setPasswordTimestamp + ", this.username=" + this.username + "]";
    }

}
