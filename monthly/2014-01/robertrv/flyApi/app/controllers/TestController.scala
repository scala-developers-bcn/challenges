package controllers

import play.api.libs.json.Json._
import play.api.mvc._
import play.api.libs.json._
import models.Flight

// you need this import to have combinators
import play.api.libs.functional.syntax._

/**
 * User: rramirez
 * Date: 1/28/14
 * Time: 7:09 PM 
 */
object TestController extends Controller{

  def index = Action {
    Ok(toJson(Map(
      "peter" -> toJson("foo"),
      "yay" -> toJson("value")
    )));
  }

  case class Person(name: String, age: Int)

  object Person {
    implicit val personReads = Json.reads[Person]
  }



  def sayHello2 = Action(parse.json) { request =>
    request.body.validate[(Int)].map{
      case (id) => Ok(Json.obj("status" ->"OK", "message" -> ("Hello "+id) ))
    }.recoverTotal{
      e => BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(e)))
    }
  }

  def sayHello = Action(parse.json) { request =>
    request.body.validate[(Flight)].map{
      case (flight) => Ok(Json.obj("status" ->"OK", "message" -> ("Hello "+flight.from+" , you're "+flight.to)))
    }.recoverTotal{
      e => BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(e)))
    }
  }

  def insert = Action {

    Ok(toJson(Map(
      "result" -> toJson("ok")
    )))
  }
}
