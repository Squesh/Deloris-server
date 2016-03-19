package backend.server.controller;

import backend.server.message_dto.HeroMovementMessage;
import backend.server.service.GlobalMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GlobalMapController {
    @Autowired
    private GlobalMapService globalMapService;

    @MessageMapping("/get-heroes")
    public void getHeroes() {
        globalMapService.getHeroes();
    }

    @MessageMapping("/register-hero")
    public void registerHero(String name) {
        globalMapService.registerHero(name);
    }

    @MessageMapping("/unregister-hero")
    public void unregisterHero(String token) {
        globalMapService.unregisterHero(token);
    }

    @MessageMapping("/move-hero")
    public void moveHero(HeroMovementMessage heroMovementMessage) {
        globalMapService.moveHero(heroMovementMessage);
    }
}
