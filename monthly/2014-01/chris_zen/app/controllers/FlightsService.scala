package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import org.joda.time.DateTime
import play.api.Logger

object FlightsService extends Controller {

  val dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
  implicit val jodaDateTimeReads = Reads.jodaDateReads(dateFormat)
  implicit val jodaDateTimeWrites = Writes.jodaDateWrites(dateFormat)

  // TODO validate status for allowed values
  val flightInfoTransform = (
    ((__ \ "id").read[String] ~> (__ \ "id").json.pickBranch) ~
    ((__ \ "from").read[String] ~> (__ \ "from").json.pickBranch) ~
    ((__ \ "to").read[String] ~> (__ \ "to").json.pickBranch) ~
    ((__ \ "departure").read[DateTime](jodaDateTimeReads) ~> (__ \ "departure").json.pickBranch) ~
    ((__ \ "arrival").read[DateTime](jodaDateTimeReads) ~> (__ \ "arrival").json.pickBranch) ~
    ((__ \ "status").read[String] ~> (__ \ "status").json.pickBranch)
    ) reduce

  var flights = Map[String, JsObject]()

  def create = Action.async(parse.json) { request =>

    request.body.transform(flightInfoTransform).map { flight =>

      //TODO ReactiveMongo
      //flights.insert(flight).map(lastError =>
      //  Created(Json.obj("ok" -> true, "id" -> flight \ "id")))

      // Dummy insert into memory, do not loose time with locks
      future {
        flights += (flight \ "id").as[String] -> flight
        Created(Json.obj("id" -> flight \ "id"))
      }
    }.getOrElse(Future.successful(BadRequest("Invalid Json input")))
  }

  def query(from: Option[String], to: Option[String]) = Action.async { request =>

    // Transform the query parameters into a JSON document
    val query: JsObject = List[Option[JsObject]](
            from.map(value => Json.obj("from" -> JsString(value))),
            to.map(value => Json.obj("to" -> JsString(value)))
    ).flatten.foldLeft(Json.obj()) { (a, b) => a ++ b }

    //TODO ReactiveMongo
    //flights.find(q).cursor[JsObject].collect[List[JsObject]].map { f =>
    //  Ok(Json.obj("ok" -> true, "flights" -> Json.toJson(f)))
    //}

    // Dummy query from memory, do not loose time with locks
    future {
      val res = flights.values
                  .filter(flight => new DateTime((flight \ "departure").as[DateTime](jodaDateTimeReads)).compareTo(DateTime.now) >= 0)
                  .filter(flight => query.fieldSet.map { case (k, v) => (flight \ k) == v}.foldLeft(true)((a, b) => a && b))

      Ok(Json.toJson(res))
    }
  }
}
