package backend.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameSessionManager gameSessionManager;

    private MessageDigest md;

    public UserService() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-256");
    }

    public UUID isCorrectUser(User user) {
        String username = user.getUsername();
        if (userRepository.findByUsernameAndPassword(username, hashPassword(user.getPassword())) != null) {
            return gameSessionManager.generateNewToken(username);
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
        byte[] digest = md.digest(password.getBytes());
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }
}
