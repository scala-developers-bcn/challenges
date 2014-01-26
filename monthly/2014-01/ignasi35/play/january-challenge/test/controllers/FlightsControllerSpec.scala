package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import repositories.FlightsRepository
import flights.Global
import repositories.InMemFlightsRepository

trait FlightsFixtures {
  val flightFixture001 = Flight("AA 289", 1393568400000L, 1393576800000L,
    "BOS", "CHI", "SCHEDULED")
}

class FlightsControllerSpec extends Specification with FlightsFixtures {

  val globalForTest = new Global {
    override def flightRepo = new InMemFlightsRepository {
      override def loadAll(): List[Flight] = List(flightFixture001)
    }
  }

  "FlightsController" should {

    "return 200 on flights/" in {
      running(FakeApplication(withGlobal = Some(globalForTest))) {
        val flights = route(FakeRequest(GET, "/flights")).get
        status(flights) must equalTo(OK)
        contentType(flights) must beSome.which(_ == "application/json")
        contentAsString(flights) must equalTo(
          """[{"id":"AA 289","arrival":1393568400000,"departure":1393576800000,"from":"BOS","to":"CHI","status":"SCHEDULED"}]""")
      }
    }

  }

}