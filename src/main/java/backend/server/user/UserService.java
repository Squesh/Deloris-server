package backend.server.user;

import backend.server.utility.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameSessionManager gameSessionManager;

    private HashUtility hashUtility = new HashUtility();

    public UUID isCorrectUser(User user) {
        String username = user.getUsername();
        if (userRepository.findByUsernameAndPassword(username, hashUtility.hashPassword(user.getPassword())) != null) {
            return gameSessionManager.generateNewToken(username);
        } else {
            return null;
        }
    }

    public boolean registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(hashUtility.hashPassword(user.getPassword()));
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
