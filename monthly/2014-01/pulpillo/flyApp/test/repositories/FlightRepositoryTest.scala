/**
 * Created by JPLOPEZ on 16/01/14.
 */
package repositories

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import models._
import repositories._
import com.github.nscala_time.time.Imports._

@RunWith(classOf[JUnitRunner])
class FlightRepositoryTest extends Specification with FlightRepositoryComponentImpl {

  "Repository" should {

    val airport = Airport(1L,"Barcelona")
    val flight = Flight(Option.empty,airport,DateTime.now,airport,DateTime.now,"STATUS")
    val createdFlight = flightRepository.createFlight(flight)
    var retrievedFlight = flightRepository.tryFindById(createdFlight.id.get)

    "Create new flight" in {
      createdFlight.id.isDefined must beTrue
    }

    "Find new created flight by Id" in {
      retrievedFlight must beSome[Flight]
      createdFlight.id must be_==(retrievedFlight.get.id)
      airport must be_==(retrievedFlight.get.from)
    }

    "Find all the flights from an airport" in {
      val flights = flightRepository.find((f:Flight) => f.from == airport)
      flights.size must be_>=(1)
    }

    "Update the status " in {
      val updatedStatus = "CHANGED STATUS"
      val updatedFlight = Flight(createdFlight.id,airport,DateTime.now,airport,DateTime.now,updatedStatus)
      flightRepository.updateFlight(updatedFlight)

      retrievedFlight = flightRepository.tryFindById(createdFlight.id.get)
      updatedStatus must be_==(retrievedFlight.get.status)
    }

    "Delete the created flight" in {
      flightRepository.delete(createdFlight.id.get)
      flightRepository.tryFindById(createdFlight.id.get) must beNone
    }
  }
}
