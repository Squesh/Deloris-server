package org.squesh.deloris.core.item.wearable.armor

import org.squesh.deloris.core.item.Item

class Armor(name: String, price: Int, _physicalArmor: Int, _magicalArmor: Int) extends Item(name, price) {
  val physicalArmor = _physicalArmor
  val magicalArmor = _magicalArmor
}