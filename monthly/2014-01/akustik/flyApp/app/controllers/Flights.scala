package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import play.api.libs.json.Json
import play.api.libs.json.JsNull

object Flights extends Controller {

  def showTo(id: Long) = Action {
    var flights = Json.obj(
      "flights" -> Json.arr(
        Json.obj(
          "id" -> "ABCD",
          "to" -> id,
          "status" -> "something"),
        Json.obj(
          "id" -> "EDFB",
          "to" -> id,
          "status" -> "something else")))
    Ok(flights.toString)
    //Ok(s"Show flights to ${id}") 
  }

}
