package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import play.api.libs.json._

object Flights extends Controller {

  case class Flight(id: String, from: String, to: String, arrival: String, departure: String, status: String)
  implicit val flightReads = Json.reads[Flight]
  implicit val flightWrites = Json.writes[Flight]

  implicit var flightDB = scala.collection.mutable.Map[String, Flight]()

  def log(msg: String) = {
    println(s"LOG: $msg")
  }

  def error(msg: String) = {
    println(s"ERROR: $msg")
  }

  def create() = Action(parse.json) {
    request => {
      request.body.validate[Flight].map{
        f => {
          log(s"Create new flight: $f")
	  flightDB.put(f.id, f).foreach(old => log("Removed previous flight: " + old))  
          Ok("done")
        }
      }.recoverTotal{
        e => {
          error(e.toString)
          BadRequest("Detected error:"+ JsError.toFlatJson(e))
        }
      }    
    }
  }

  def to(id: String) = Action {
    var flightsTo = flightDB.filter(_._2.to == id).map(_._2)
    Ok(Json.toJson(flightsTo)) 
  }

}
