package repositories

import model.Flight
import play.api.Logger

class InMemoryFlightsRepository extends FlightsRepository {
  var data = scala.collection.mutable.Map[String, Flight]()

  private val timestampInRange = (t: Long, from: Long, to: Long) => (from, to) match {
    case (0, 0) => true
    case (0, b) => t <= b
    case (a, 0) => a <= t
    case (a, b) => a <= t && t <= b
  }

  def insert(f: Flight) = {
    data.put(f.id, f).foreach(old => Logger.info("Removed previous flight: " + old))
  }

  private def filter(sel: (Flight) => String, ts: (Flight) => Long)(id: String, from: Long, to: Long) = {
    data.filter {
      case (flightId, flight) => sel(flight) == id && timestampInRange(ts(flight), from, to)
    }.values
  }

  def flightsTo(id: String, from: Long, to: Long): Iterable[Flight] = {
    filter(f => f.to, f => f.arrival)(id, from, to)
  }

  def flightsFrom(id: String, from: Long, to: Long): Iterable[Flight] = {
    filter(f => f.from, f => f.arrival)(id, from, to)
  }

  def updateStatus(id: String, status: String) = {
    data.get(id) match {
      case Some(f) => {
        insert(Flight(f.id, f.from, f.to, f.arrival, f.departure, status))
      }
      case None => throw new IllegalStateException(s"Flight ${id} not found")
    }
  }

  def delete(id: String) = {
    data -= id
  }
}