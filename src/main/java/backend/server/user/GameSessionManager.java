package backend.server.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameSessionManager {
    Map<String, UUID> playersTokens = new HashMap<>();

    public UUID generateNewToken(String username) {
        if (playersTokens.containsKey(username)) {
            return null;
        } else {
            UUID token = UUID.randomUUID();
            playersTokens.put(username, token);
            return token;
        }
    }

    public void shutdownSession(String username, UUID token) {
        playersTokens.remove(username, token);
    }
}
