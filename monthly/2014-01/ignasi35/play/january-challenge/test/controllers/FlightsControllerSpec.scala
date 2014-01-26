package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import repositories.FlightsRepository
import repositories.InMemFlightsRepository
import play.api.libs.json._
import controllers.Protocol._
import play.api.mvc.AnyContent
import play.api.mvc.Action
import play.api.mvc.Request

trait FlightsFixtures {
  val flightFixture001 = Flight("AA 289", 1393568400000L, 1393576800000L,
    "BOS", "CHI", "SCHEDULED")
  val flightFixture002 = Flight("VX 925", 1393568400000L, 1393576800000L,
    "LAX", "SFO", "SCHEDULED")
}

class FlightsControllerSpec extends Specification with FlightsFixtures {
  def flightRepo = new InMemFlightsRepository {
    override def loadAll(): List[Flight] = List(flightFixture001)
  }

  "FlightsController" should {

    "return 200 on flights/" in {
      val flightsAction: Action[AnyContent] = new FlightsController().getFlights(flightRepo)
      val flights = flightsAction.apply(FakeRequest())

      status(flights) must equalTo(OK)
      contentType(flights) must beSome.which(_ == "application/json")
      contentAsString(flights) must equalTo(
        """[{"id":"AA 289","arrival":1393568400000,"departure":1393576800000,"from":"BOS","to":"CHI","status":"SCHEDULED"}]""")
    }

    "return 201 when POSTing a new flight to flights/" in {
      running(FakeApplication()) {
        val body = Json.toJson(flightFixture002)
        val flights = route(FakeRequest(POST, "/flights").withBody(body)).get
        status(flights).toInt must equalTo(201)
      }
    }
    "return 400 when POSTing an invalid JSON to flights/" in {
      running(FakeApplication()) {
        val flights = route(FakeRequest(POST, "/flights").withBody("""{"foo":"bar"}""")).get
        status(flights).toInt must equalTo(400)
      }
    }

  }

}