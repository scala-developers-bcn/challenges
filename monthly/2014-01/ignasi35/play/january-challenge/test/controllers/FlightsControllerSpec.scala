package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import repositories.FlightsRepository
import flights.Global
import repositories.InMemFlightsRepository

class FlightsControllerSpec extends Specification {

  val globalForTest = new Global {
    override def flightRepo = new InMemFlightsRepository {
      override def loadAll(): List[Flight] =
        List(Flight("ON_TIME", "BOS", "CHG", "19B"))
    }
  }

  "FlightsController" should {

    "return 200 on flights/" in {
      running(FakeApplication(withGlobal = Some(globalForTest))) {
        val flights = route(FakeRequest(GET, "/flights")).get
        status(flights) must equalTo(OK)
        contentType(flights) must beSome.which(_ == "application/json")
        contentAsString(flights) must equalTo(
          """[{"status":"ON_TIME","origin":"BOS","destiny":"CHG","gate":"19B"}]""")
      }
    }

  }

}