package models

import play.api.libs.json.Json

/**
 * User: rramirez
 * Date: 1/17/14
 * Time: 5:40 PM 
 */

object FlightStatus extends Enumeration {
  type FlightStatus = Value
  val CANCELLED, READY, ON_TIME, DELAYED, ON_AIR, LANDED = Value
}

case class Flight(id: Long, arrival: String, departure: String, from: String, to:String) {
  import FlightStatus._


}

object Flight {
  //http://www.playframework.com/documentation/2.2.x/ScalaJsonInception
  implicit val implicitReader = Json.reads[Flight]
}
