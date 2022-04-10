package catalog.classes;

import catalog.utils.Validators;

public class User {

    private String username;
    private String email;
    private String password;
    private boolean adminRights;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        if (Validators.isValidEmail(email)) {
            this.password = Integer.toString(password.hashCode());
        }
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdminRights() {
        return false;
    }
}
