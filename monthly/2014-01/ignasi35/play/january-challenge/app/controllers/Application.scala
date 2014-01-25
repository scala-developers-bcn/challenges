package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

case class Flight(status: String, origin: String, destiny: String, gate: String)

object Protocol {
  implicit val fmtFlight = Json.format[Flight]
}

trait FlightRepository {
  def loadAll(): List[Flight]
}

class InMemFlightsRepository extends FlightRepository {
  private val flights = List(Flight("ON_TIME", "BOS", "CHG", "19B"))
  def loadAll(): List[Flight] = flights
}

class Application(flightRepo: FlightRepository) extends Controller {

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
