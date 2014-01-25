package repositories

import controllers.Flight

trait FlightsRepository {
  def loadAll(): List[Flight]
}

class InMemFlightsRepository extends FlightsRepository {
  private val flights = List(Flight("ON_TIME", "BOS", "CHG", "19B"))
  def loadAll(): List[Flight] = flights
}

