package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.libs.json.JsNull
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.Airport
import models.Flight
import services.FlightServiceComponent

trait FlightsController extends Controller {

  self: FlightServiceComponent =>

  //Use Play Json Inception but is experimental
  //See http://www.playframework.com/documentation/2.2.0/ScalaJsonInception for more details
  implicit val airportJSONFormat = Json.format[Airport]
  implicit val flightJSONFormat = Json.format[Flight]

  def showTo(id: Long) = Action {
    showFlights((f:Flight) =>  f.to.id == id)
  }

  def showFrom(id: Long) = Action {
    showFlights((f:Flight) => f.from.id == id)
  }

   def showFlights(f : Flight => Boolean)  = {

    val flights = flightService.findFlights(f)
    if (flights.isDefined) {
      Ok(Json.toJson(flights))
    } else {
      NotFound
    }
  }

  def create() = Action(parse.json) { request =>
    unmarshalFlightResource(request, (resource: Flight) => {
      val flight = Flight(Option.empty,
        resource.from,resource.departure,
        resource.to,resource.arrival,
        resource.status)
      flightService.createFlight(flight)
      Created
    })
  }

  def delete(id : Long) = Action {
    flightService.deleteFlight(id)
    NoContent
  }

  def updateStatus(id: Long) = Action(parse.json) {request =>
    unmarshalFlightResource(request, (resource: Flight) => {
      val flight = flightService.tryFindById(id)
      flight match {
        case None => NotFound
        case Option => {
          flightService.updateFlight(flight.copy(status = resource.status))
          NoContent
        }
      }
    })
  }

  private def unmarshalFlightResource(request: Request[JsValue],
                                      block: (Flight) => Result): Result ={
    request.body.validate[Flight].fold(
      valid = block,
      invalid = ( e => {
        val error = e.mkString
        Logger.error(error)
        BadRequest(error)
      })
    )
  }

}
