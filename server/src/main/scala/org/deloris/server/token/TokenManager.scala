package org.deloris.server.token

import java.util.UUID

import scala.collection.mutable

class TokenManager {
  val map = new mutable.HashMap[UUID, UUID]() // session token and userId
}
