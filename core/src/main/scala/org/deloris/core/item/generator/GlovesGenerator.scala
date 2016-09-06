package org.deloris.core.item.generator

import org.deloris.core.item.wearable.armor.Gloves

class GlovesGenerator extends ItemGenerator[Gloves] {
  val applicableEffects = List("physicalResist", "magicResist", "speedBonus", "speedPenalty", "hpIncrease") // todo: real effect classes instead of strings

  def generate(price: Int): Gloves = {

  }
}
