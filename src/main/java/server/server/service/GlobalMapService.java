package server.server.service;

import server.core.hero.Hero;
import server.server.message.HeroMovementMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GlobalMapService {
    @Autowired
    private HeroService heroService;
    @Autowired
    private SimpMessagingTemplate template;

    public void getHeroes() {
        template.convertAndSend("/topic/init-heroes", heroService.getHeroes());
    }

    public void registerHero(String name) {
        Hero hero = heroService.registerHero(name);
        template.convertAndSend("/topic/registering-hero", hero);
    }

    public void unregisterHero(String token) {
        heroService.unregisterHero(token);
    }

    public void moveHero(HeroMovementMessage heroMovementMessage) {
        heroService.moveHero(heroMovementMessage);
        template.convertAndSend("/topic/heroes", heroService.getHeroes());
    }
}
