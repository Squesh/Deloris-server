package org.squesh.deloris.core.hero

import org.squesh.deloris.core.item.Item

class ItemStack(_item: Item, _count: Int) {
  val item = _item
  var count = _count
}
