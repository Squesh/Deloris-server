package com.squesh.deloris.server.server.controller

import com.squesh.deloris.server.core.Player
import com.squesh.deloris.server.server.message.PlayerMovementMessage
import com.squesh.deloris.server.server.service.PlayerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin

@Controller
@CrossOrigin
class GameController {
    @Autowired
    PlayerService playerService

    @MessageMapping("/get-players")
    @SendTo("/topic/init-players")
    List<Player> getPlayers() {
        return playerService.players
    }

    @MessageMapping("/register-player")
    @SendTo("/topic/registering-player")
    Player registerPlayer(String name) {
        return playerService.registerPlayer(name)
    }

    @MessageMapping("/move-player")
    @SendTo("/topic/players")
    List<Player> movePlayer(PlayerMovementMessage playerMovementMessage) {
        playerService.movePlayer(playerMovementMessage)

        return playerService.players
    }
}
