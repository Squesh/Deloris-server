package org.squesh.deloris.server.user

import scala.collection.mutable

/**
 * todo: simple, in-memory implementation
 */

object UserStorage {
  val users = new mutable.HashMap[String, String]() // login -> password ?

  def isUserExisted(name: String): Boolean = {
    users.contains(name)
  }

  def checkCredentials(credentials: Credentials): Boolean = {
    users.getOrElse(credentials.name, "") == HashApplier.hashString(credentials.password)
  }

  def registerUser(credentials: Credentials): Unit = {
    users.put(credentials.name, HashApplier.hashString(credentials.password))
  }
}
