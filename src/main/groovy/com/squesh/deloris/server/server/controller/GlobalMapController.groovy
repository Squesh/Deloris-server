package com.squesh.deloris.server.server.controller

import com.squesh.deloris.server.server.message.HeroMovementMessage
import com.squesh.deloris.server.server.service.GlobalMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class GlobalMapController {
    @Autowired
    GlobalMapService globalMapService

    @MessageMapping("/get-heroes")
    void getHeroes() {
        globalMapService.getHeroes()
    }

    @MessageMapping("/register-hero")
    void registerHero(String name) {
        globalMapService.registerHero(name)
    }

    @MessageMapping("/unregister-hero")
    void unregisterHero(String token) {
        globalMapService.unregisterHero(token)
    }

    @MessageMapping("/move-hero")
    void moveHero(HeroMovementMessage heroMovementMessage) {
        globalMapService.moveHero(heroMovementMessage)
    }
}