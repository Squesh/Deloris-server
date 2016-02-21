package com.squesh.deloris.server.server.controller

import com.squesh.deloris.server.core.Hero
import com.squesh.deloris.server.server.message.HeroMovementMessage
import com.squesh.deloris.server.server.service.GlobalMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin

@Controller
class GlobalMapController {
    @Autowired
    GlobalMapService globalMapService

    @MessageMapping("/get-heroes")
    List<Hero> getHeroes() {
        globalMapService.heroes
    }

    @MessageMapping("/register-hero")
    Hero registerHero(String name) {
        globalMapService.registerHero(name)
    }

    @MessageMapping("/unregister-hero")
    void unregisterHero(String token) {
        globalMapService.unregisterHero(token)
    }

    @MessageMapping("/move-hero")
    List<Hero> moveHero(HeroMovementMessage heroMovementMessage) {
        globalMapService.moveHero(heroMovementMessage)
        globalMapService.heroes
    }
}
