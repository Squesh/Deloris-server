package org.squesh.deloris.server.user

object UserService {
  def login(credentials: Credentials) : Any = {
    UserStorage.checkCredentials(credentials) match {
      case true => SessionManager.getToken(credentials.name)
      case false => false
    }
  }

  def registerNewUser(credentials: Credentials) : Boolean = {
    UserStorage.isUserExisted(credentials.name) match {
      case true => false // todo: disgusting true=>false traverse
      case false => UserStorage.registerUser(credentials); true
    }
  }
}
