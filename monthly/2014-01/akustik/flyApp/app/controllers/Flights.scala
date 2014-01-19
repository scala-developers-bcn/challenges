package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._

import play.api.libs.json._

case class Flight(id: String, from: String, to: String,
  arrival: Long, departure: Long, status: String)

trait Logging {
  def log(msg: String) = {
    println(s"LOG: $msg")
  }

  def error(msg: String) = {
    println(s"ERROR: $msg")
  }
}

trait FlightDB extends Logging {
  def insert(f: Flight)
  def flightsTo(id: String, from: Long, to: Long): Iterable[Flight]
  def flightsFrom(id: String, from: Long, to: Long): Iterable[Flight]
  def updateStatus(id: String, status: String)
  def delete(id: String)
}

class MemoryFlightDB extends FlightDB {
  var flightDB = scala.collection.mutable.Map[String, Flight]()

  private val timestampInRange = (t: Long, from: Long, to: Long) => (from, to) match {
    case (0, 0) => true
    case (0, b) => t <= b
    case (a, 0) => a <= t
    case (a, b) => a <= t && t <= b
  }

  def insert(f: Flight) = {
    flightDB.put(f.id, f).foreach(old => log("Removed previous flight: " + old))
  }

  private def filter(sel: (Flight) => String, ts: (Flight) => Long)(id: String, from: Long, to: Long) = {
    flightDB.filter {
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
    flightDB.get(id) match {
      case Some(f) => {
        insert(Flight(f.id, f.from, f.to, f.arrival, f.departure, status))
      }
      case None => throw new IllegalStateException(s"Flight ${id} not found")
    }
  }

  def delete(id: String) = {
    flightDB -= id
  }
}

object Flights extends Controller with Logging {

  implicit val flightReads = Json.reads[Flight]
  implicit val flightWrites = Json.writes[Flight]
  implicit val db = new MemoryFlightDB

  private val responseSuccess = Ok(Json.obj("result" -> "success"))
  private val responseFailure = Ok(Json.obj("result" -> "failure"))
  private val responseJsonError = BadRequest("json parse error")
  private val responseFlights = (flights: Iterable[Flight]) => Ok(Json.toJson(flights))

  private def validateAndExecute[T](validate: (JsValue) => JsResult[T],
    operate: (T) => Unit) = Action(parse.json) {
    request =>
      {
        validate(request.body).map {
          in =>
            {
              try {
                operate(in)
                responseSuccess
              } catch {
                case e: Throwable => {
                  error(e.toString())
                  responseFailure
                }
              }

            }
        }.recoverTotal {
          e =>
            {
              error(e.toString)
              responseJsonError
            }
        }
      }
  }

  private def list(block: => Iterable[Flight]) = Action {
    try {
      responseFlights(block)
    } catch {
      case e: Throwable => {
        error(e.toString())
        responseFailure
      }
    }
  }

  private def execute(block: => Unit) = Action {
    try {
      block
      responseSuccess
    } catch {
      case e: Throwable => {
        error(e.toString())
        responseFailure
      }
    }
  }

  def create() = {
    validateAndExecute(_.validate[Flight], (f: Flight) => db.insert(f))
  }

  def to(id: String, from: Long, to: Long) = list {
    db.flightsTo(id, from, to)
  }

  def from(id: String, from: Long, to: Long) = list {
    db.flightsFrom(id, from, to)
  }

  def delete(id: String) = execute {
    db.delete(id)
  }

  def updateStatus(id: String) = validateAndExecute(_.validate[String], (status: String) => {
    db.updateStatus(id, status)
  })

}
