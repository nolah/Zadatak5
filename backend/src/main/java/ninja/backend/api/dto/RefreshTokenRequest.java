package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class RefreshTokenRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> REFRESH_TOKEN = new PropertyPath<>("refreshToken");

    @NotNull
    @Size(max = 255)
    private String refreshToken;

    private RefreshTokenRequest() {
    }

    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final RefreshTokenRequest other = (RefreshTokenRequest) obj;
        if (this.refreshToken != null && other.refreshToken != null && !this.refreshToken.equals(other.refreshToken))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.refreshToken == null) ? 0 : this.refreshToken.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RefreshTokenRequest";
    }

}
