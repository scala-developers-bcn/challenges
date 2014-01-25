package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

object FlightDomain {
  type CityCode = String

  case class Flight // (status: FlightStatus , origin: CityCode , destiny: CityCode,
  (status: String, origin: String, destiny: String,
    gate: String)

  class FlightStatus extends Enumeration {
    val SCHEDULED, DELAYED, CANCELLED, BOARDING, ON_AIR, LANDED = Value
  }

  object FlightStatus {
    def unapply(fs: FlightStatus) = fs.toString
  }

  object Protocol {
    //implicit val fmtCityCode = Json.format[CityCode]
    //implicit val fmtFlightStatus = Json.format[FlightStatus]
    implicit val fmtFlight = Json.format[Flight]
  }
}

object Application extends Controller {

  import FlightDomain._
  import FlightDomain.Protocol._

  def addFlight = Action { NotFound }

  def getFlights = Action { Ok(Json.toJson(List())) }

  // TODO filter by date range
  def getFlightsFrom(cityCode: String) = Action { NotFound }

  // TODO filter by date range
  def getFlightsTo(cityCode: String) = Action { NotFound }

  def updateFlight = Action { NotFound }

  // def delete

}
