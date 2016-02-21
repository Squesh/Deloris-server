package com.squesh.deloris.server.server.service

import com.squesh.deloris.server.core.Hero
import com.squesh.deloris.server.server.message.HeroMovementMessage
import org.springframework.stereotype.Service

@Service
class HeroService {
    List<Hero> heroes = []
    def random = new Random()

    Hero registerHero(String name) {
        def hero = new Hero(UUID.randomUUID().toString(), name, random.nextInt(20), random.nextInt(20))
        heroes << hero
        return hero
    }

    void moveHero(HeroMovementMessage move) {
        heroes.find({ it.token == move.token }).each({ it.x = move.newX; it.y = move.newY })
    }

    void unregisterHero(String token) {
        heroes.removeAll({ it.token == token })
    }
}
