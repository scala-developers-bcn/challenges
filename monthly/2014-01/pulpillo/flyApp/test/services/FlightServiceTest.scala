/**
 * Created by JPLOPEZ on 16/01/14.
 */

package services

import org.specs2.mutable._
import org.specs2.runner._
import com.github.nscala_time.time.Imports._
import org.junit.Assert._
import org.specs2.mock._
import org.junit.runner._

import repositories._
import models.Flight
import models.Airport
import services._

class FlightServiceTest extends Specification
with FlightServiceComponentImpl
with FlightRepositoryMockComponent {

  "Service" should {

    val airport =     Airport(1, "Barcelona")

    "Create new flight" in {
      val flight = Flight(Option.empty, Airport(1, "Barcelona"), DateTime.now, Airport(2, "Madrid"), DateTime.now, "STATUS")
      flightService.createFlight(flight)
      there was one(flightRepository).createFlight(flight)
    }

    "Find created flight by id" in {
      val id = 1L
      val flight = Flight(Option(id), Airport(1, "Barcelona"), DateTime.now, Airport(2, "Madrid"), DateTime.now, "STATUS")
      flightRepository.tryFindById(id) returns Option(flight)

      val retrievedFlight = flightService.tryFindById(id)

      assertEquals(flight, retrievedFlight.get)
      there was one(flightRepository).tryFindById(id)
    }

    "Update the status " in {
      val updatedStatus = "CHANGED STATUS"
      val flight = Flight(Option(1L), airport, DateTime.now, airport, DateTime.now, updatedStatus)
      flightService.updateFlight(flight)
      there was one(flightRepository).updateFlight(flight)
    }

    "Find created flight by id" in {
      val id = 1L
      val flight = Flight(Option(id), Airport(1, "Barcelona"), DateTime.now, Airport(2, "Madrid"), DateTime.now, "STATUS")
      flightRepository.tryFindById(id) returns Option(flight)

      val retrievedFlight = flightService.tryFindById(id)

      assertEquals(flight, retrievedFlight.get)
      there was two(flightRepository).tryFindById(id)
    }

    "Find non existent flight " in {
      val id = 1000L
      flightRepository.tryFindById(id) returns Option.empty

      val retrievedFlight = flightService.tryFindById(id)

      assertEquals(Option.empty, retrievedFlight)
      there was one(flightRepository).tryFindById(id)
    }

    "Delete a flight" in {
      val id = 1L
      flightService.deleteFlight(id)
      there was one(flightRepository).delete(id)
    }
  }
}

trait FlightRepositoryMockComponent extends FlightRepositoryComponent with Mockito {

  override val flightRepository = mock[FlightRepository]

}