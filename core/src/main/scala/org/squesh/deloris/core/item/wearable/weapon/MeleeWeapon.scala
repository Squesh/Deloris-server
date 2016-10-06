package org.squesh.deloris.core.item.wearable.weapon

import org.squesh.deloris.core.damage.Damage

class MeleeWeapon(_name: String, _damage: Damage, _price: Int, _range: Int, _attackSpeed: Double, _startAngle: Int,
                  _endAngle: Int, _weaponSpeed: Int) extends Weapon(_name, _damage, _price, _range, _attackSpeed) {
  val startAngle = _startAngle
  val endAngle = _endAngle
  val weaponSpeed = _weaponSpeed
}