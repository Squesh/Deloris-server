package backend.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameSessionManager gameSessionManager;

    public UUID isCorrectUser(User user) {
        if (userRepository.findByUsernameAndPassword(user.getUsername(), hashPassword(user.getPassword())) != null) {
            return gameSessionManager.registerNewToken();
        } else {
            return null;
        }
    }

    public boolean registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(hashPassword(user.getPassword()));
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    private String hashPassword(String password) {
        // todo
        return password + "super_hash_suffix";
    }
}
