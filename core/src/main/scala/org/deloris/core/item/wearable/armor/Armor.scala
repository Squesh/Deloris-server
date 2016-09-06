package org.deloris.core.item.wearable.armor

import org.deloris.core.item.Item

class Armor(name: String, price: Int, _armorLevel: Int) extends Item(name, price) {
  val armorLevel: Int = _armorLevel
}