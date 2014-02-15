package com.example

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._
import scala.util.Random

class MyFlightsServiceSpec extends Specification with Specs2RouteTest with MyFlightsService {

  import FlightProtocol._

  //Not sure why I need this import...
  import spray.httpx.SprayJsonSupport._

  def actorRefFactory = system

  var id: String = "0"
  var destination: String = "Barcelona"
  var origin: String = "Regensburg"
  
  "MyService" should {

    "return a greeting for GET requests to /flights/[flightId]" in {
      Get("/flights/" + id) ~> myRoute ~> check {
        status === OK
        handled must beTrue
        responseAs[String] === "GET a flight"
      }
    }

    "return a greeting for GET requests to /flights/to/[destination]" in {
      Get("/flights/to/" + destination) ~> myRoute ~> check {
        status === OK
        handled must beTrue
        responseAs[String] === "GET destination flights"
      }
    }

    "return a greeting for GET requests to /flights/from/[origin]" in {
      Get("/flights/from/" + origin) ~> myRoute ~> check {
        status === OK
        handled must beTrue
        responseAs[String] === "GET origin flights"
      }
    }

    "return a greeting for PUT requests to /flights/[flightId]" in {
      Put("/flights/" + id, Flight(id, DateTime(2014,1,1,0,0,0).clicks, DateTime(2014,1,1,6,0,0).clicks, "Barcelona", "Regensburg", "UNKNOWN")) ~> myRoute ~> check {
        status === OK
        handled must beTrue
        responseAs[String] === "PUT a flight"
      }
    }

    "return a greeting for DELETE requests to /flights/[flightId]" in {
      Delete("/flights/" + id) ~> myRoute ~> check {
        status === OK
        handled must beTrue
        responseAs[String] === "DELETE a flight"
      }
    }

    "return a greeting for POST requests to /flights" in {
      Post("/flights", Flight(id, DateTime(2014,1,1,0,0,0).clicks, DateTime(2014,1,1,6,0,0).clicks, "Barcelona", "Regensburg", "UNKNOWN")) ~> myRoute ~> check {
        status === OK
        handled must beTrue
        responseAs[String] === "POST a new flight"
      }
    }

  }
}
