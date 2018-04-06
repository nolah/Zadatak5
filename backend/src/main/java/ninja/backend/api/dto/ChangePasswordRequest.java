package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class ChangePasswordRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> OLD_PASSWORD = new PropertyPath<>("oldPassword");
    public static final PropertyPath<String> NEW_PASSWORD = new PropertyPath<>("newPassword");

    @NotNull
    @Size(min = 6, max = 32)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*&@%+/_'!#$^?:.(\\)\\[\\]{}~\\-]{8,}$")
    private String oldPassword;

    @NotNull
    @Size(min = 6, max = 32)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*&@%+/_'!#$^?:.(\\)\\[\\]{}~\\-]{8,}$")
    private String newPassword;

    private ChangePasswordRequest() {
    }

    public ChangePasswordRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ChangePasswordRequest other = (ChangePasswordRequest) obj;
        if (this.oldPassword != null && other.oldPassword != null && !this.oldPassword.equals(other.oldPassword))
            return false;
        if (this.newPassword != null && other.newPassword != null && !this.newPassword.equals(other.newPassword))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.oldPassword == null) ? 0 : this.oldPassword.hashCode());
        result = prime * result + ((this.newPassword == null) ? 0 : this.newPassword.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ChangePasswordRequest";
    }

}
