package catalog.classes;

public class Admin extends User {
    public Admin(String username, String email, String password) {
        super(username, email, password);
    }

    @Override
    public boolean isAdminRights() {
        return true;
    }
}
