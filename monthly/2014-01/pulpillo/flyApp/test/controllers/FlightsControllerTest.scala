/**
 * Created by JPLOPEZ on 15/01/14.
 */

package controllers

import scala.concurrent.Await
import org.junit.Assert._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api._
import play.api.mvc._
import play.api.libs.json._

import org.specs2.mutable._
import org.specs2.runner._
import com.github.nscala_time.time.Imports._
import org.specs2.mock._
import controllers._
import services._
import models._

@RunWith(classOf[JUnitRunner])
class FlightsControllerTest extends Specification  {

  val flightController = new FlightsController with FlightServiceMockComponent{}
  val airport = Airport(1L,"Barcelona")
  val flight = Flight(Option.empty,airport,DateTime.now,airport,DateTime.now,"STATUS")

  implicit val airportJSONFormat = Json.format[Airport]
  implicit val flightJSONFormat = Json.format[Flight]

  "Controller" should {

    "Create new flight" in {
      val request = FakeRequest(POST, "/flights")
        .withBody(
          Json.toJson(flight)
        )

      val hey = flightController.create()(request)
      status(hey) must equalTo(201)
      //there was one(flightController.flightService).createFlight(flight)
    }

    "Update the status " in {
      val id = 1L
      val updatedStatus = "CHANGED STATUS"
      val flight = Flight(Option(1L), airport, DateTime.now, airport, DateTime.now, updatedStatus)

      val request = FakeRequest(PUT, s"/flights/$id")
        .withBody(
          Json.toJson(flight)
        )

      val hey = flightController.updateStatus(id)(request)
      status(hey) must equalTo(204)
      //there was one(flightController.flightService).updateFlight(flight)
    }
  }
}

trait FlightServiceMockComponent extends FlightServiceComponent with Mockito {

  override val flightService = mock[FlightService]
}
