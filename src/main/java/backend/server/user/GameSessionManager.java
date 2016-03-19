package backend.server.user;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class GameSessionManager {
    private Set<UUID> tokens;

    public UUID registerNewToken() {
        UUID uuid = UUID.randomUUID();
        tokens.add(uuid);
        return uuid;
    }

    public void deregisterToken(UUID uuid) {
        tokens.remove(uuid);
    }
}
