import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._
import repositories.RecordingFlightsRepository
import repositories.FlightsRepository
import org.specs2.specification.Scope
import org.specs2.execute.AsResult
import play.api.GlobalSettings

@RunWith(classOf[JUnitRunner])
class FlightsSpec extends Specification with FlightData {

  "Flights controller" should {

    "send 404 on a bad request" in new TestEnv.RecordingScope {
      running(recordingApp) {
        route(FakeRequest(GET, "/boum")) must beNone
      }
    }

    "create a new flight" in new TestEnv.RecordingScope {
      running(recordingApp) {

        val newFlight1 = route(FakeRequest("POST", "/flight/new").withJsonBody(flight1)).get

        status(newFlight1) must equalTo(OK)
        contentType(newFlight1) must beSome.which(_ == "application/json")
        charset(newFlight1) must beSome("utf-8")
        contentAsString(newFlight1) must equalTo(Json.obj("result" -> "success").toString)

        flightRepo.lastInserted match {
          case Some(flight) => {
            (flight1 \ "id").as[String] must equalTo(flight.id)
            (flight1 \ "to").as[String] must equalTo(flight.to)
            (flight1 \ "from").as[String] must equalTo(flight.from)
            (flight1 \ "arrival").as[Long] must equalTo(flight.arrival)
            (flight1 \ "departure").as[Long] must equalTo(flight.departure)
            (flight1 \ "status").as[String] must equalTo(flight.status)
          }
          case None => failure("No flight has been found")
        }
      }
    }

    "return an error when creating a flight with incomplete information" in new TestEnv.RecordingScope {
      running(recordingApp) {
        val newIncompleteFlight = route(FakeRequest("POST", "/flight/new").withJsonBody(incompleteFlight)).get

        status(newIncompleteFlight) must equalTo(BAD_REQUEST)
        contentType(newIncompleteFlight) must beSome.which(_ == "text/plain")
        charset(newIncompleteFlight) must beSome("utf-8")
        contentAsString(newIncompleteFlight) must contain("json parse error")

        flightRepo.lastInserted match {
          case Some(x) => failure("A flight has been found")
          case None => success
        }
      }
    }

    "return an empty list of flights to somewhere" in new TestEnv.RecordingScope {
      running(recordingApp) {
        val flightsTo = route(FakeRequest(GET, "/flights/to/somewhere")).get

        status(flightsTo) must equalTo(OK)
        contentType(flightsTo) must beSome.which(_ == "application/json")
        charset(flightsTo) must beSome("utf-8")
        contentAsString(flightsTo) must equalTo(Json.arr().toString)

        flightRepo.lastFlightsTo match {
          case Some((id, from, to)) => {
            id must equalTo("somewhere")
            from must equalTo(0)
            to must equalTo(0)
          }
          case _ => failure("No last flight found")
        }
      }
    }

    "return an empty list of flights to somewhere with an arrival equal or after a timestamp" in new TestEnv.RecordingScope {
      running(recordingApp) {
        val flightsTo = route(FakeRequest(GET, "/flights/to/a/123")).get
        status(flightsTo) must equalTo(OK)
        contentType(flightsTo) must beSome.which(_ == "application/json")
        charset(flightsTo) must beSome("utf-8")
        contentAsString(flightsTo) must equalTo(Json.arr().toString)

        flightRepo.lastFlightsTo match {
          case Some((id, from, to)) => {
            id must equalTo("a")
            from must equalTo(123)
            to must equalTo(0)
          }
          case _ => failure("No last flight found")
        }
      }
    }
  }
}

