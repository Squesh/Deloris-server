package backend.core.hero.inventory;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Inventory {
    private Multiset<Item> items = HashMultiset.create();
}
