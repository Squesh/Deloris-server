package server.core.hero.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * items, currently equipped on hero
 */

public class Equipment {
    private Map<ItemSlot, Item> equipment = new HashMap<>();

    public Equipment() {
        for (ItemSlot itemSlot : ItemSlot.values()) {
            equipment.put(itemSlot, null);
        }
    }

    public void equip(Item item) {

    }
}
