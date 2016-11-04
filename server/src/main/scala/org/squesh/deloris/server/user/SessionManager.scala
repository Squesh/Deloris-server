package org.squesh.deloris.server.user

import java.util.UUID

import scala.collection.mutable

object SessionManager {

  val sessionTokens = new mutable.HashMap[String, UUID]()

  def endSession(name: String): Unit = {
    sessionTokens.remove(name)
  }

  def getToken(name: String): UUID = {
    sessionTokens.getOrElseUpdate(name, UUID.randomUUID())
  }
}