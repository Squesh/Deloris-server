package backend.core.hero.inventory;

/**
 * such items like potions, magic wands, wards, scrolls, etc
 */

public class UsableItem extends Item {
    public void use() {
        System.out.println("i am used!");
    }
}
