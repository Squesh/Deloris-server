package org.deloris.core.item.wearable.weapon

import org.deloris.core.damage.Damage

class RangeWeapon(_name: String, _damage: Damage, _price: Int, _range: Int, _attackSpeed: Double, _projectile: Projectile)
  extends Weapon(_name, _damage, _price, _range, _attackSpeed) {
  val projectile = _projectile
}
