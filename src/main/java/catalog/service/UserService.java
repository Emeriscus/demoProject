package catalog.service;

import catalog.classes.User;
import catalog.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public boolean isExistingUser(String username) {
        return userRepository.isExistingUser(username);
    }
}
