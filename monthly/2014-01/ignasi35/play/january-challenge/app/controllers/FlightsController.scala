package controllers

import play.api.libs.json._
import play.api.mvc._
import repositories.FlightsRepository
import views.html.defaultpages.badRequest
import repositories.InMemFlightsRepository

case class Flight(id: String, arrival: Long, departure: Long,
  from: String, to: String, status: String)

object Protocol {
  implicit val fmtFlight = Json.format[Flight]
}

class FlightsController extends Controller {
  import Protocol._

  def addFlight(implicit flightRepo: FlightsRepository) = Action(parse.json) {
    request =>
      request.body.validate[Flight] match {
        case JsSuccess(f, _) =>
          flightRepo.add(f)
          Created
        case JsError(_) => BadRequest
      }
  }
  def getFlights(implicit flightRepo: FlightsRepository) = Action {
    Ok(Json.toJson(flightRepo.loadAll))
  }
}

object FlightsController extends Controller {

  private lazy val defaultCtlr = new FlightsController

  implicit private lazy val defaultFligthsRepo: FlightsRepository = new InMemFlightsRepository

  def addFlight = defaultCtlr.addFlight

  def getFlights = defaultCtlr.getFlights

  // TODO filter by date range
  def getFlightsFrom(cityCode: String) = Action { NotFound }
  def getFlightsTo(cityCode: String) = Action { NotFound }
  def updateFlight = Action { NotFound }

}
