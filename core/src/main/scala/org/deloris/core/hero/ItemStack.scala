package org.deloris.core.hero

import org.deloris.core.item.Item

class ItemStack(_item: Item, _count: Int) {
  val item = _item
  var count = _count
}
