package org.squesh.deloris.server.user

import java.security.MessageDigest

object HashApplier {

  val md = MessageDigest.getInstance("SHA-256")

  def hashString(input: String): String = {
    val digest = md.digest(input.getBytes())
    return String.format("%064x", new java.math.BigInteger(1, digest))
  }
}