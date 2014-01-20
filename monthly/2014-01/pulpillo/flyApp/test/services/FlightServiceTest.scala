/**
 * Created by JPLOPEZ on 16/01/14.
 */

package services

import org.specs2.mutable._
import org.specs2.runner._
import org.mockito.Mockito._
import com.github.nscala_time.time.Imports._
import org.junit.Assert._

import repositories._
import models.Flight
import models.Airport
import services._

@RunWith(classOf[JUnitRunner])
class FlightServiceTest extends Specification
with FlightServiceComponentImpl
with FlightRepositoryMockComponent {

  "Service" should {

    "Create new flight" in {
      val flight = Flight(Option.empty, Airport(1, "Barcelona"), DateTime.now, Airport(2, "Madrid"), DateTime.now, "STATUS")
      flightService.createFlight(flight)
      verify(flightRepository).createFlight(flight)
    }

    "Find created flight by id" in {
      val id = 1L
      val flight = Flight(Option(id), Airport(1, "Barcelona"), DateTime.now, Airport(2, "Madrid"), DateTime.now, "STATUS")
      when(flightRepository.tryFindById(id)).thenReturn(Option(flight))

      val retrievedFlight = flightService.tryFindById(id)

      assertEquals(flight, retrievedFlight.get)
      verify(flightRepository).tryFindById(id)
    }

    "Update the status " in {
      val updatedStatus = "CHANGED STATUS"
      val flight = Flight(createdFlight.id, airport, DateTime.now, airport, DateTime.now, updatedStatus)
      flightService.updateFlight(flight)
      verify(flightRepository).updateFlight(flight)
    }

    "Find created flight by id" in {
      val id = 1L
      val flight = Flight(Option(id), Airport(1, "Barcelona"), DateTime.now, Airport(2, "Madrid"), DateTime.now, "STATUS")
      when(flightRepository.tryFindById(id)).thenReturn(Option(flight))

      val retrievedFlight = flightService.tryFindById(id)

      assertEquals(flight, retrievedFlight.get)
      verify(flightRepository).tryFindById(id)
    }

    "Find non existent flight " in {
      val id = 1000L
      when(flightRepository.tryFindById(id)).thenReturn(Option.empty)

      val retrievedFlight = flightService.tryFindById(id)

      assertEquals(Option.empty, retrievedFlight)
      verify(flightRepository).tryFindById(id)
    }

    "Delete a flight" in {
      val id = 1L
      flightService.delete(id)
      verify(flightRepository).delete(id)
    }
  }
}

trait FlightRepositoryMockComponent extends FlightRepositoryComponent {

  override val flightRepository = mock(classOf[FlightRepository])

}