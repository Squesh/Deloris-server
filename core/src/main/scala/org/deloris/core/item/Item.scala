package org.deloris.core.item

abstract class Item(_name: String, _price: Int) {
  val name: String = _name
  val price: Int = _price
}