package org.squesh.deloris.server.user

import scala.collection.mutable

/**
 * todo: simple, in-memory implementation
 */

object UserStorage {
  val users = new mutable.HashMap[String, User]()

  def isUserExisted(user: User): Boolean = {
    users.contains(user.name)
  }

  def registerUser(user: User): Unit = {
    users.put(user.name, user)
  }
}
