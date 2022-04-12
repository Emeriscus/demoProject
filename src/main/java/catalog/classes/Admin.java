package catalog.classes;

public class Admin extends User {

    public Admin(String email, String salt, String securePassword) {
        super(email, salt, securePassword);
    }

    @Override
    public boolean isAdminRights() {
        return true;
    }
}
