package org.deloris.server

import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.stream.ActorMaterializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.collection.mutable
import scala.io.StdIn

case class User(name: String, age: Int)

object WebServer extends Directives{
  def main(args: Array[String]) {

    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule)
    val map = new mutable.HashMap[String, User]()

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()

    implicit val executionContext = system.dispatcher

    implicit val um = {
      Unmarshaller.stringUnmarshaller.map { (string) =>
        objectMapper.readValue(string, classOf[User])
      }
    }

    val route =
      path("user") {
        post {
          entity(as[User]) { result =>
            complete {
              map.put(UUID.randomUUID().toString, result)
              ""
            }
          }
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