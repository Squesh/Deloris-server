package server.server.service;

import server.core.Hero;
import server.server.message.HeroMovementMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HeroService {
    private List<Hero> heroes = new ArrayList<Hero>();
    private Random random = new Random();

    public Hero registerHero(String name) {
        Hero hero = new Hero(UUID.randomUUID().toString(), name, random.nextInt(20), random.nextInt(20));
        heroes.add(hero);
        return hero;
    }

    public void moveHero(HeroMovementMessage move) {
        // todo: find hero with this token and update coordinates
    }

    public void unregisterHero(String token) {
        // todo: remove hero with this token from list
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
