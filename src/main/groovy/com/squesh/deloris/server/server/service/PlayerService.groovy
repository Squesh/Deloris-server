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
        players
                .stream()
                .filter( { player -> player.token == move.token } )
                .forEach( { player -> player.x = move.newX; player.y = move.newY } )
    }

    void deregisterPlayer(String token) {
        players.removeIf( {player -> player.token == token} )
    }
}
