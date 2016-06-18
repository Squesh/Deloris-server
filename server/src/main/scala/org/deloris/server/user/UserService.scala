package org.deloris.server.user

import java.util.UUID

object UserService {
  def login(user: User) : Any = {
    UserStorage.isUserExisted(user) match {
      case true => UUID.randomUUID() // todo: add token to SessionManager
      case false => false
    }
  }

  def registerNewUser(user: User) : Boolean = {
    UserStorage.isUserExisted(user) match {
      case true => false
      case false => { UserStorage.registerUser(user); true }
    }
  }
}
