package org.squesh.deloris.core.item.wearable.weapon

import org.squesh.deloris.core.damage.Damage
import org.squesh.deloris.core.item.Item

class Weapon(_name: String, _damage: Damage, _price: Int, _range: Int, _attackSpeed: Double) extends Item(_name, _price) {
  val damage = _damage // todo: composite damage is not possible in this design
  val range = _range
  val attackSpeed = _attackSpeed
}
