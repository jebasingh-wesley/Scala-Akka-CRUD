package com.crud

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.io.StdIn

trait JsonSupport {
  implicit val personFormat = jsonFormat2(Person)
}

object AkkaHttpApi extends App with JsonSupport {

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "akka-http-api")
  implicit val materializer: Materializer = Materializer(system)
  implicit val executionContext = system.executionContext

  val route: Route =
    concat(
      path("persons") {
        get {
          complete(FileUtil.readFile())
        } ~
          post {
            entity(as[Person]) { person =>
              FileUtil.addPerson(person)
              complete(StatusCodes.Created, person)
            }
          }
      },
      path("persons" / Segment) { name =>
        put {
          entity(as[Person]) { updatedPerson =>
            if (FileUtil.updatePerson(name, updatedPerson)) {
              complete(StatusCodes.OK, updatedPerson)
            } else {
              complete(StatusCodes.NotFound, s"Person with name $name not found.")
            }
          }
        } ~
          delete {
            if (FileUtil.deletePerson(name)) {
              complete(StatusCodes.NoContent)
            } else {
              complete(StatusCodes.NotFound, s"Person with name $name not found.")
            }
          }
      }
    )

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

  println("Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}