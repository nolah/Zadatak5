package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import ninja.backend.model.enumeration.*;
import eu.execom.fabut.property.PropertyPath;


public class SignInResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> ACCESS_TOKEN = new PropertyPath<>("accessToken");
    public static final PropertyPath<String> REFRESH_TOKEN = new PropertyPath<>("refreshToken");
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<String> USERNAME = new PropertyPath<>("username");
    public static final PropertyPath<Role> ROLE = new PropertyPath<>("role");

    @NotNull
    @Size(min = 64, max = 64)
    private String accessToken;

    @NotNull
    @Size(min = 64, max = 64)
    private String refreshToken;

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 128)
    private String username;

    @NotNull
    private Role role;

    private SignInResponse() {
    }

    public SignInResponse(String accessToken, String refreshToken, Long id, String username, Role role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SignInResponse other = (SignInResponse) obj;
        if (this.accessToken != null && other.accessToken != null && !this.accessToken.equals(other.accessToken))
            return false;
        if (this.refreshToken != null && other.refreshToken != null && !this.refreshToken.equals(other.refreshToken))
            return false;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.username != null && other.username != null && !this.username.equals(other.username))
            return false;
        if (this.role != null && other.role != null && !this.role.equals(other.role))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.accessToken == null) ? 0 : this.accessToken.hashCode());
        result = prime * result + ((this.refreshToken == null) ? 0 : this.refreshToken.hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SignInResponse[" + "this.id=" + this.id + ", this.username=" + this.username + ", this.role=" + this.role + "]";
    }

}
