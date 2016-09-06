package org.deloris.core.item.generator

import org.deloris.core.item.Item

trait ItemGenerator[T <: Item] {
  def generate(price: Int): T
}
