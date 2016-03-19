package backend.server.game.service;

import backend.core.hero.Hero;
import backend.server.game.message_dto.HeroMovementMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GlobalMapService {
    @Autowired
    private HeroService heroService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void getHeroes() {
        messagingTemplate.convertAndSend("/topic/init-heroes", heroService.getHeroes());
    }

    public void registerHero(String name) {
        Hero hero = heroService.registerHero(name);
        messagingTemplate.convertAndSend("/topic/registering-hero", hero);
    }

    public void unregisterHero(String token) {
        heroService.unregisterHero(token);
    }

    public void moveHero(HeroMovementMessage heroMovementMessage) {
        heroService.moveHero(heroMovementMessage);
        messagingTemplate.convertAndSend("/topic/heroes", heroService.getHeroes());
    }
}
