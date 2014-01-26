package controllers

import play.api.libs.json._
import play.api.mvc._
import repositories.FlightsRepository
import views.html.defaultpages.badRequest

case class Flight(id: String, arrival: Long, departure: Long,
  from: String, to: String, status: String)

object Protocol {
  implicit val fmtFlight = Json.format[Flight]
}

class FlightsController(flightRepo: FlightsRepository) extends Controller {

  import Protocol._
  def getFlights = Action {
    Ok(Json.toJson(flightRepo.loadAll))
  }

  def addFlight = Action(parse.json) { request =>
    request.body.validate[Flight] match {
      case JsSuccess(f, _) =>
        flightRepo.add(f)
        Created
      case JsError(_) => BadRequest
    }
  }
  // TODO filter by date range
  def getFlightsFrom(cityCode: String) = Action { NotFound }
  def getFlightsTo(cityCode: String) = Action { NotFound }
  def updateFlight = Action { NotFound }

}
