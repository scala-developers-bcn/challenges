package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._

import play.api.libs.json._

object Flights extends Controller {

  case class Flight(id: String, from: String, to: String,
    arrival: Long, departure: Long, status: String)

  implicit val flightReads = Json.reads[Flight]
  implicit val flightWrites = Json.writes[Flight]

  implicit var flightDB = scala.collection.mutable.Map[String, Flight]()

  val responseSuccess = Ok(Json.obj("result" -> "success"))
  val responseFailure = Ok(Json.obj("result" -> "failure"))
  val responseJsonError = BadRequest("json parse error")
  val responseFlights = (flights: Iterable[Flight]) => Ok(Json.toJson(flights))

  def log(msg: String) = {
    println(s"LOG: $msg")
  }

  def error(msg: String) = {
    println(s"ERROR: $msg")
  }

  def insert(f: Flight) = {
    flightDB.put(f.id, f).foreach(old => log("Removed previous flight: " + old))
  }

  def validateOrFailure[T](validate: (JsValue) => JsResult[T], operate: (T) => Unit) = Action(parse.json) {
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

  def create() = validateOrFailure(_.validate[Flight], insert)

  val timestampInRange = (t: Long, from: Long, to: Long) => (from, to) match {
    case (0, 0) => true
    case (0, b) => t <= b
    case (a, 0) => a <= t
    case (a, b) => a <= t && t <= b
  }

  def filter(sel: (Flight) => String, ts: (Flight) => Long)(id: String, from: Long, to: Long) = {
    flightDB.filter {
      case (flightId, flight) => sel(flight) == id && timestampInRange(ts(flight), from, to)
    }.values
  }

  val flightsTo = filter(f => f.to, f => f.arrival)_
  val flightsFrom = filter(f => f.from, f => f.arrival)_

  def to(id: String, from: Long, to: Long) = Action {
    responseFlights(flightsTo(id, from, to))
  }

  def from(id: String, from: Long, to: Long) = Action {
    responseFlights(flightsFrom(id, from, to))
  }

  def delete(id: String) = Action {
    flightDB -= id
    responseSuccess
  }

  def updateStatus(id: String) = validateOrFailure(_.validate[String], (status: String) => {
    flightDB.get(id) match {
      case Some(f) => {
        insert(Flight(f.id, f.from, f.to, f.arrival, f.departure, status))
      }
      case None => throw new IllegalStateException(s"Flight ${id} not found")
    }
  })
}
