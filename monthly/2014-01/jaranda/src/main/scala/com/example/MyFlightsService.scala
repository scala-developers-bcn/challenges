package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import spray.json.DefaultJsonProtocol

//TODO: Use enums, real-life IDs, and so forth... (issues found with JSON marshalling/unmarshalling)
case class Flight(id: String, arrival: Long, departure: Long, from: String, to: String, status: String)

object FlightProtocol extends DefaultJsonProtocol {
  implicit val flightFormat = jsonFormat6(Flight);
}

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyFlightsService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait MyFlightsService extends HttpService {

  import FlightProtocol._

  //Not sure why I need this import...
  import spray.httpx.SprayJsonSupport._
  import spray.httpx.marshalling.BasicMarshallers

  val myRoute =

    pathPrefix("flights") {
      path("to" / Segment) {
        destination =>
          get {
            rejectEmptyResponse {
              complete {
                "GET destination flights"
              }
            }
          }
      } ~ path("from" / Segment){
        origin =>
          get {
            rejectEmptyResponse {
              complete {
                "GET origin flights"
              }
            }
          }
      } ~ path(Segment){
        flightId =>
          get {
            rejectEmptyResponse {
              complete {
                "GET a flight"
              }
            }
          } ~ put {
            entity(as[Flight]){
              flight =>
                complete {
                  "PUT a flight"
                }
            }
          } ~ delete {
            complete {
              "DELETE a flight"
            }
          }
      } ~ post {
        entity(as[Flight]){
          flight =>
            complete {
              "POST a new flight"
            }
        }
      }
    }
}