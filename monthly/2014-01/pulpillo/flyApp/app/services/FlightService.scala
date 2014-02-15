/**
 * Created by JPLOPEZ on 14/01/14.
 */

package services

import models.Flight
import repositories.FlightRepositoryComponent

trait FlightServiceComponent {

  val flightService: FlightService

  trait FlightService {

    def createFlight(flight: Flight): Flight

    def updateFlight(flight: Flight)

    def tryFindById(id:Long): Option[Flight]

    def findFlights(f:Flight => Boolean): Option[List[Flight]]

    def deleteFlight(id: Long)

  }

}

trait FlightServiceComponentImpl extends FlightServiceComponent {
  self: FlightRepositoryComponent =>

  override val flightService = new FlightServiceImpl

  class FlightServiceImpl extends FlightService {

    override def createFlight(flight: Flight): Flight = {
      flightRepository.createFlight(flight)
    }

    override def updateFlight(flight: Flight) {
      flightRepository.updateFlight(flight)
    }

    override def tryFindById(id:Long): Option[Flight] = {
      flightRepository.tryFindById(id)
    }

    override def findFlights(f:Flight => Boolean): Option[List[Flight]] = {
      flightRepository.find(f)
    }

    override def deleteFlight(id: Long) {
      flightRepository.delete(id)
    }
  }
}
