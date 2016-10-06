package org.squesh.deloris.server.user

import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol._

import scala.io.StdIn

object UserController extends Directives {
  def main(args: Array[String]) {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    implicit val userFormat = jsonFormat2(User)

    val route = {
      post {
        path("registration") {
          entity(as[User]) { user =>
            UserService.registerNewUser(user) match {
              case true => complete(StatusCodes.OK)
              case false => complete(StatusCodes.Forbidden)
            }
          }
        }
      } ~
      post {
        path("login") {
          entity(as[User]) { user =>
            UserService.login(user) match {
              case uuid: UUID => complete(uuid.toString)
              case _ => complete(StatusCodes.Forbidden)
            }
          }
        }
//        pathPrefix("reg" / Remaining) { key =>
//          entity(as[User]) { user =>
//            complete {
//              StatusCodes.OK
//            }
//          }
//        }
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}