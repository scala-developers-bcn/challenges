import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import play.api.libs.json._

@RunWith(classOf[JUnitRunner])
class FlightsSpec extends Specification with FlightData {

  "Flights" should {

    "send 404 on a bad request" in {
      running(FakeApplication()) {
        route(FakeRequest(GET, "/boum")) must beNone
      }
    }

    "create a new flight" in {
      running(FakeApplication()) {
        val newFlight1 = route(FakeRequest("POST", "/flight/new").withJsonBody(flight1)).get

        status(newFlight1) must equalTo(OK)
        contentType(newFlight1) must beSome.which(_ == "application/json")
        charset(newFlight1) must beSome("utf-8")
        contentAsString(newFlight1) must equalTo(Json.obj("result" -> "success").toString)
      }
    }

    "return an error when creating a flight with incomplete information" in {
      running(FakeApplication()) {
        val newIncompleteFlight = route(FakeRequest("POST", "/flight/new").withJsonBody(incompleteFlight)).get

        status(newIncompleteFlight) must equalTo(BAD_REQUEST)
        contentType(newIncompleteFlight) must beSome.which(_ == "text/plain")
        charset(newIncompleteFlight) must beSome("utf-8")
        contentAsString(newIncompleteFlight) must contain("json parse error")
      }
    }

    "return an empty list of flights to somewhere" in {
      running(FakeApplication()) {
        val flightsTo = route(FakeRequest(GET, "/flights/to/somewhere")).get

        status(flightsTo) must equalTo(OK)
        contentType(flightsTo) must beSome.which(_ == "application/json")
        charset(flightsTo) must beSome("utf-8")
        contentAsString(flightsTo) must equalTo(Json.arr().toString)
      }
    }
    
    "return an empty list of flights to somewhere with an arrival equal or after a timestamp" in {
      running(FakeApplication()) {
        val flightsTo = route(FakeRequest(GET, "/flights/to/a/123")).get
        status(flightsTo) must equalTo(OK)
        contentType(flightsTo) must beSome.which(_ == "application/json")
        charset(flightsTo) must beSome("utf-8")
        contentAsString(flightsTo) must equalTo(Json.arr().toString)
      }
    }
  }
}
