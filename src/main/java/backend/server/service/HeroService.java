package backend.server.service;

import backend.core.hero.Hero;
import backend.server.message_dto.HeroMovementMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HeroService {
    private List<Hero> heroes = new ArrayList<>();
    private Random random = new Random();

    public Hero registerHero(String name) {
        Hero hero = new Hero(UUID.randomUUID().toString(), name, random.nextInt(20), random.nextInt(20));
        heroes.add(hero);
        return hero;
    }

    public void moveHero(HeroMovementMessage move) {
        heroes.stream().filter(hero -> hero.getToken().equals(move.getToken())).forEach(hero -> {
            hero.setX(move.getNewX());
            hero.setY(move.getNewY());
        });
    }

    public void unregisterHero(String token) {
        heroes.removeIf(hero -> hero.getToken().equals(token));
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
