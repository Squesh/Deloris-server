package org.deloris.core.hero

import org.deloris.core.item.Item

class ItemStack(_item: Item, _count: Int) {
  val item: Item = _item
  var count: Int = _count
}
