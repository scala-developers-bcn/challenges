package repositories

import controllers.Flight

trait FlightsRepository {
  def loadAll(): List[Flight]
}

class InMemFlightsRepository extends FlightsRepository {
  private val flights = List()
  def loadAll(): List[Flight] = flights
}

