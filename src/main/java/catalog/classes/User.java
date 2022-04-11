package catalog.classes;

import catalog.utils.Validators;

public class User {

    private String email;
    private String salt;
    private String securePassword;
    private boolean adminRights;

    public User(String email, String salt, String securePassword) {
        this.email = email;
        this.salt = salt;
        this.securePassword = securePassword;
        this.adminRights = false;
    }

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getSalt() {
        return salt;
    }

    public String getSecurePassword() {
        return securePassword;
    }

    public boolean isAdminRights() {
        return adminRights;
    }

}
