package com.squesh.deloris.server.server.service

import com.squesh.deloris.server.core.Player
import com.squesh.deloris.server.server.message.PlayerMovementMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GlobalMapService {
    @Autowired
    PlayerService playerService

    List<Player> getPlayers() {
        playerService.players
    }

    Player registerPlayer(String name) {
        playerService.registerPlayer(name)
    }

    void unregisterPlayer(String token) {
        playerService.unregisterPlayer(token)
    }

    void movePlayer(PlayerMovementMessage playerMovementMessage) {
        playerService.movePlayer(playerMovementMessage)
    }
}
