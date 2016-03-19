package backend.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.server.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void tryLogin(String username, String password) {

    }

    public void registerNewUser(String username, String plainPassword) {
        if (userRepository.findByUsername(username) == null) {
            //
        } else {
            // username is already taken
        }
    }

    private String hashPassword(String password) {
        return password;
    }
}
