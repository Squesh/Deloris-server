package org.deloris.core.hero

class Hero(_name: String) {
  val name = _name
  val inventory = new Inventory()
  val equipment = new Equipment()
  val characterInfo = new CharacterInfo()
}