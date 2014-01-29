package controllers

import play.api._
import play.api.mvc._
import models.FlightStatus._

import play.api.libs.json._
import models.Flight


/**
 *
 * Your task for Jan 2014 is to complete a REST API for the FlyApp. That is, you don't have to worry about presentation, persistance or any other detail but you have to provide HTTP/JSON endpoints to perform all operations of the FlyApp. To be more specific, you must support:

    POST a flight (id, arrival, departure, from, to, status)
    GET a list of flights to X [within time]
    GET a list of flights from Y [within time]
    PUT an updated status to a flight (i.e: DELAYED, CANCELLED, Gate 42)
    DELETE a flight (maybe)

 */
object Application extends Controller {

  def search(from:Option[String],to:Option[String]) = Action {
    if (from.isEmpty && to.isEmpty)
      Forbidden(Json.obj("status" ->"KO", "message" -> "You have to specify at least from or to"))
    else
      Ok(Json.obj("from" ->from, "to"->to,"result"->"ok"))
  }

  def create = Action(parse.json) { request =>
    request.body.validate[(Flight)].map{
      case (flight) => Created(Json.obj("status" ->"OK", "message" -> ("Created flight : "+flight)))
    }.recoverTotal{
      e => BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(e)))
    }
  }

  def modify(id: Long) = Action(parse.json) { request =>
      request.body.validate[String].map{
        case (status) => {
          if (id == 666)
            NotFound(Json.obj("status" ->"KO", "message" -> Json.toJson(s"Can't find flight $id")))
          else
            Ok(Json.obj("status" ->"OK", "message" -> (s"new status '$status' saved for flight#$id")))
        }
      }.recoverTotal{
        e => BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(e)))
      }
  }

  def delete(id: Long) = Action {
    if (id == 666)
      NotFound(Json.obj("status" ->"KO", "message" -> Json.toJson(s"Can't find flight $id")))
    else
      Ok(Json.obj("status" -> "OK"))
  }

  def index = Action {
    Ok(Json.obj("message" ->"Welcome to the sample API for flights",
      "actions" -> Json.toJsFieldJsValueWrapper(Seq(
        Json.toJson("GET a list of flights to X [within time]"),
        Json.toJson("GET a list of flights from Y [within time]"),
        Json.toJson("PUT an updated status to a flight (i.e: DELAYED, CANCELLED, Gate 42)"),
        Json.toJson("DELETE a flight (maybe)")
      ))))
  }

  def default = Action {
    Ok(views.html.index("Your new application is ready."))
  }
}