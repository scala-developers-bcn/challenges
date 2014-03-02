package repositories

import scala.concurrent.Future

import model.Flight
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class InMemoryFlightsRepository extends FlightsRepository {
  var data = scala.collection.mutable.Map[String, Flight]()

  private val timestampInRange = (t: Long, from: Long, to: Long) => (from, to) match {
    case (0, 0) => true
    case (0, b) => t <= b
    case (a, 0) => a <= t
    case (a, b) => a <= t && t <= b
  }

  def insert(f: Flight) = Future {
    data.put(f.id, f) match {
      case Some(oldFlight) => {
        Logger.error("A flight already existed with this id: " + oldFlight)
        false
      }
      case None => true
    }
  }

  private def filter(sel: (Flight) => String, ts: (Flight) => Long)(id: String, from: Long, to: Long) = {
    data.filter {
      case (flightId, flight) => sel(flight) == id && timestampInRange(ts(flight), from, to)
    }.values.toList
  }

  def flightsTo(id: String, from: Long, to: Long): Future[List[Flight]] = {
    Future {
      filter(f => f.to, f => f.arrival)(id, from, to)
    }
  }

  def flightsFrom(id: String, from: Long, to: Long):  Future[List[Flight]] = {
    Future {
      filter(f => f.from, f => f.arrival)(id, from, to)
    }
  }

  def updateStatus(id: String, status: String) = {
    data.get(id) match {
      case Some(f) => insert(Flight(f.id, f.from, f.to, f.arrival, f.departure, status))
      case None => Future {
        Logger.error(s"Flight with id ${id} not found")
        false
      }
    }
  }

  def delete(id: String) = Future {
    data -= id
    true
  }
}