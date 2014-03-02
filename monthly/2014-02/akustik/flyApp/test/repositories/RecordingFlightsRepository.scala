package repositories

import scala.concurrent.Future

import model.Flight
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class RecordingFlightsRepository extends FlightsRepository {

  var lastInserted = None : Option[Flight]
  var lastDeleted = None: Option[String]
  var lastStatusUpdated = None: Option[(String, String)]
  var lastFlightsTo = None: Option[(String, Long, Long)]
  var lastFlightsFrom = None: Option[(String, Long, Long)]
  
  def reset() = {
    lastInserted = None
    lastDeleted = None
    lastStatusUpdated = None
    lastFlightsTo = None
    lastFlightsFrom = None
  }
  
  def insert(f: Flight) = Future {
    lastInserted = Some(f)
    true
  }

  def flightsTo(id: String, from: Long, to: Long): Future[List[Flight]] = Future {
    lastFlightsTo = Some((id, from, to))
    List()
  }

  def flightsFrom(id: String, from: Long, to: Long): Future[List[Flight]] = Future {
    lastFlightsFrom = Some((id, from, to))
    List()
  }

  def updateStatus(id: String, status: String) = Future {
    lastStatusUpdated = Some((id, status))
    true
  }

  def delete(id: String) = Future {
    lastDeleted = Some(id)
    true
  }
}