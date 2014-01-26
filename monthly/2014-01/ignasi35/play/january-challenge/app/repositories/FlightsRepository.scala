package repositories

import controllers.Flight
import scala.collection.mutable.Set

trait FlightsRepository {
  def loadAll(): List[Flight]
  def add(f: Flight): Unit
}

class InMemFlightsRepository extends FlightsRepository {
  private val flights: Set[Flight] = Set()
  def loadAll(): List[Flight] = flights.toList
  def add(f: Flight) = flights.add(f)
}

