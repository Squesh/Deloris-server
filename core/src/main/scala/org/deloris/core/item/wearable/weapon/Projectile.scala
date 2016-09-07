package org.deloris.core.item.wearable.weapon

import org.deloris.core.effect.Effect

class Projectile(_size: Int, _effect: Effect) {
  val size = _size
  val effectOnCollision = _effect
}
