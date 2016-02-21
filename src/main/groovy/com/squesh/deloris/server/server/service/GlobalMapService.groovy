package com.squesh.deloris.server.server.service

import com.squesh.deloris.server.core.Hero
import com.squesh.deloris.server.server.message.HeroMovementMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service

@Service
class GlobalMapService {
    @Autowired
    HeroService heroService

    @SendTo("/topic/init-heroes")
    List<Hero> getHeroes() {
        heroService.heroes
    }

    @SendTo("/topic/registering-hero")
    Hero registerHero(String name) {
        heroService.registerHero(name)
    }

    void unregisterHero(String token) {
        heroService.unregisterHero(token)
    }

    @SendTo("/topic/heroes")
    void moveHero(HeroMovementMessage heroMovementMessage) {
        heroService.moveHero(heroMovementMessage)
    }
}
