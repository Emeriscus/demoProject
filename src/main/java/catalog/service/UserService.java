package catalog.service;

import catalog.classes.User;
import catalog.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public String getStoredPasswordByEmail(String email) {
        return userRepository.getStoredPasswordByEmail(email);
    }

    public String getStoredSaltByEmail(String email) {
        return userRepository.getStoredSaltByEmail(email);
    }

    public boolean hasAdminRightByEmail(String email) {
        return userRepository.hasAdminRightByEmail(email);
    }

    public boolean isExistingUser(String email) {
        try {
            userRepository.queryByEmail(email);
            return true;
        } catch (EmptyResultDataAccessException erdae) {
            System.out.println("No such user with this e-mail: " + email + "!");
            return false;
        }
    }
}
