package controllers

import scala.concurrent.Future

import model.Flight
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax.functionalCanBuildApplicative
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.JsResult
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.mvc.Action
import play.api.mvc.Controller
import repositories.FlightsRepository

object Flights {
  val jsonSuccess = Json.obj("result" -> "success")
  val jsonFailure = Json.obj("result" -> "failure")
  val jsonErrorMsg = "json parse error"
  def jsonExecutionError(msg: String) = Json.obj("result" -> "failure", "detail" -> msg)
}

class Flights(flightsRepo: FlightsRepository) extends Controller {

  implicit val flightReads = Json.reads[Flight]
  implicit val flightWrites = Json.writes[Flight]

  private val responseSuccess = Ok(Flights.jsonSuccess)
  private val responseFailure = Ok(Flights.jsonFailure)
  private val responseJsonError = BadRequest(Flights.jsonErrorMsg)
  private val responseFlights = (flights: Iterable[Flight]) => Future {
    Ok(Json.toJson(flights))
  }

  private def validateAndExecuteAsync[T](validate: (JsValue) => JsResult[T],
    operate: (T) => Future[Boolean]) = Action.async(parse.json) {
    request =>
      {
        validate(request.body).map {
          in => operate(in).map(_ match {
            case true => responseSuccess
            case false => responseFailure
          })
        }.recoverTotal {
          e =>
            {
              Logger.error(e.toString)
              Future{responseJsonError}
            }
        }
      }
  }

  def create() = validateAndExecuteAsync(_.validate[Flight], 
    (f: Flight) => flightsRepo.insert(f))

  def to(id: String, from: Long, to: Long) = Action.async {
    flightsRepo.flightsTo(id, from, to).flatMap(responseFlights)
  }

  def from(id: String, from: Long, to: Long) = Action.async {
    flightsRepo.flightsFrom(id, from, to).flatMap(responseFlights)
  }

  def delete(id: String) = validateAndExecuteAsync(_.validate[String],
    (body: String) => flightsRepo.delete(id))

  def updateStatus(id: String) = validateAndExecuteAsync(_.validate[String], (status: String) => {
    flightsRepo.updateStatus(id, status)
  })
}
