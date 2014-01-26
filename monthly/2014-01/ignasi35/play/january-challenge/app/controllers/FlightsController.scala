package controllers

import play.api.libs.json._
import play.api.mvc._
import repositories.FlightsRepository

case class Flight(status: String, origin: String, destiny: String, gate: String)

object Protocol {
  implicit val fmtFlight = Json.format[Flight]
}

class FlightsController(flightRepo: FlightsRepository) extends Controller {

  import Protocol._
  def getFlights = Action {
    Ok(Json.toJson(flightRepo.loadAll))
  }

  def addFlight = Action { NotFound }
  // TODO filter by date range
  def getFlightsFrom(cityCode: String) = Action { NotFound }
  def getFlightsTo(cityCode: String) = Action { NotFound }
  def updateFlight = Action { NotFound }

}
