package com.squesh.deloris.server.server.service

import com.squesh.deloris.server.core.Player
import com.squesh.deloris.server.server.message.PlayerMovementMessage
import org.springframework.stereotype.Service

@Service
class PlayerService {
    List<Player> players = []
    def random = new Random()

    Player registerPlayer(String name) {
        def player = new Player(UUID.randomUUID().toString(), name, random.nextInt(20), random.nextInt(20))
        players << player
        return player
    }

    void movePlayer(PlayerMovementMessage move) {
        players.find({ it.token == move.token }).each({ it.x = move.newX; it.y = move.newY })
    }

    void unregisterPlayer(String token) {
        players.removeAll({ it.token == token })
    }
}
