package com.squesh.deloris.server.server.service

import com.squesh.deloris.server.core.Hero
import com.squesh.deloris.server.server.message.HeroMovementMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class GlobalMapService {
    @Autowired
    HeroService heroService

    @Autowired
    SimpMessagingTemplate template;

    void getHeroes() {
        template.convertAndSend("/topic/init-heroes", heroService.heroes)
    }

    void registerHero(String name) {
        def hero = heroService.registerHero(name)
        template.convertAndSend("/topic/registering-hero", hero)
    }

    void unregisterHero(String token) {
        heroService.unregisterHero(token)
    }

    void moveHero(HeroMovementMessage heroMovementMessage) {
        heroService.moveHero(heroMovementMessage)
        template.convertAndSend("/topic/heroes", heroService.heroes)
    }
}
