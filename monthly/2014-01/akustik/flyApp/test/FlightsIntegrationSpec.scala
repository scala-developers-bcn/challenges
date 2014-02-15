import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import play.api.libs.json._

@RunWith(classOf[JUnitRunner])
class FlightsIntegrationSpec extends Specification with FlightData {

  "Flights" should {
    "show the flights that have been created with an specific destination" in {
      running(FakeApplication()) {

        val newFlight1 = route(FakeRequest("POST", "/flight/new").withJsonBody(flight1)).get

        status(newFlight1) must equalTo(OK)
        contentAsString(newFlight1) must equalTo(Json.obj("result" -> "success").toString)

        val location = (flight1 \ "to").as[String]
        val flightsTo = route(FakeRequest(GET, s"/flights/to/${location}")).get

        status(flightsTo) must equalTo(OK)
        contentAsString(flightsTo) must equalTo(Json.arr(flight1).toString)

        val flightsToAnother = route(FakeRequest(GET, s"/flights/to/another")).get

        status(flightsToAnother) must equalTo(OK)
        contentAsString(flightsToAnother) must equalTo(Json.arr().toString)
      }
    }
   
    "be able to update the status of an existing flight" in {
      running(FakeApplication()) {

        val newFlight1 = route(FakeRequest("POST", "/flight/new").withJsonBody(flight1)).get

        status(newFlight1) must equalTo(OK)
        contentAsString(newFlight1) must equalTo(Json.obj("result" -> "success").toString)
        
        val newStatus = "Some new status"
        val id = (flight1 \ "id").as[String]
        val location = (flight1 \ "to").as[String]
        
        route(FakeRequest(PUT, s"/flight/${id}/status").withJsonBody(Json.obj("value" -> newStatus))).get
      
        val flightsTo = route(FakeRequest(GET, s"/flights/to/${location}")).get

        status(flightsTo) must equalTo(OK)
        contentAsString(flightsTo) must equalTo(Json.arr(flight1).toString)
      }
    }
  }
}
