package com.squesh.deloris.server.server.controller

import com.squesh.deloris.server.core.Player
import com.squesh.deloris.server.server.message.PlayerMovementMessage
import com.squesh.deloris.server.server.service.GlobalMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin

@Controller
@CrossOrigin
class GlobalMapController {
    @Autowired
    GlobalMapService globalMapService

    @MessageMapping("/get-players")
    @SendTo("/topic/init-players")
    List<Player> getPlayers() {
        globalMapService.players
    }

    @MessageMapping("/register-player")
    @SendTo("/topic/registering-player")
    Player registerPlayer(String name) {
        globalMapService.registerPlayer(name)
    }

    @MessageMapping("/unregister-player")
    void unregisterPlayer(String token) {
        globalMapService.unregisterPlayer(token)
    }

    @MessageMapping("/move-player")
    @SendTo("/topic/players")
    List<Player> movePlayer(PlayerMovementMessage playerMovementMessage) {
        globalMapService.movePlayer(playerMovementMessage)
        globalMapService.players
    }
}
