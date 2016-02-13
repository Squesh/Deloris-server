package com.squesh.deloris.server

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin

@Controller
@CrossOrigin
class GameController {
    def players = [new Player(10, 10), new Player(5, 5), new Player(1, 1)]

    @MessageMapping("/get-players")
    @SendTo("/topic/init-players")
    List<Player> getPlayers() {
        return players
    }

    @MessageMapping("/move-player")
    @SendTo("/topic/players")
    List<Player> movePlayer(PlayerMovementMessage playerMovementMessage) {
        players[playerMovementMessage.id].x = playerMovementMessage.newX
        players[playerMovementMessage.id].y = playerMovementMessage.newY

        return players
    }
}
