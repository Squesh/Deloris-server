package org.deloris.core.item.generator

import org.deloris.core.damage.Damage
import org.deloris.core.item.wearable.armor.Gloves

class WeaponGenerator extends ItemGenerator[Damage] { // Damage is not subtype of item - but no compile error
  val applicableEffects = List("physicalResist", "magicResist", "speedBonus", "speedPenalty", "hpIncrease") // todo: real effect classes instead of strings

  override def generate(price: Int): Gloves = { // Gloves is not Damage but no error too
    new Gloves()
  }
}
