package repositories

import model.Flight

import play.api.Logger
import play.api.libs.json._
import play.modules.reactivemongo._
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.Play.current

import reactivemongo.api._
import reactivemongo.bson._

import scala.concurrent.duration.Duration
import scala.concurrent.{ Future, Await }

/**
 * Check this out:
 *   coast-to-coast: http://www.playframework.com/documentation/2.1.3/ScalaJsonTransformers
 *   reactive mongo for play: https://github.com/ReactiveMongo/Play-ReactiveMongo
 *   http://docs.mongodb.org/manual/tutorial/query-documents/
 */
class MongoDBFlightsRepository extends FlightsRepository {

  case class FlightWithId(_id: String)

  val timeout = Duration(1000, "millis")

  private def flights: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("flights")

  implicit val flightFormat = Json.format[Flight]

  implicit val flightWithIdFormat = Json.format[FlightWithId]

  def insert(f: Flight) = {
    flights.insert(f).map(lastError => Logger.error("lastError = " + lastError))
  }

  private def addFromClause(query: JsObject, from: Long) = {
    if(from != 0) {
      val jsonTransformer = (__ \ "arrival").json.put(Json.obj("$gte" -> from))
      query.transform(jsonTransformer).getOrElse({
        Logger.error("Unable to add from clause")
        query
      })
    } else {
      query
    }
  }
  
  private def addToClause(query: JsObject, to: Long) = {
    if(to != 0) {
      val jsonTransformer = (__ \ "arrival").json.put(Json.obj("$lte" -> to))
      query.transform(jsonTransformer).getOrElse({
        Logger.error("Unable to add to clause")
        query
      })
    } else {
      query
    }
  }
  
  def flightsTo(id: String, from: Long, to: Long): Iterable[Flight] = {
    val query = addToClause(addFromClause(Json.obj("to" -> id), from), to)
    val cursor: Cursor[Flight] = flights.find(query).cursor[Flight]
    val futureFlightsList: Future[List[Flight]] = cursor.collect[List]()
    Await.result(futureFlightsList, timeout)
  }

  def flightsFrom(id: String, from: Long, to: Long): Iterable[Flight] = {
    val query = addToClause(addFromClause(Json.obj("from" -> id), from), to)
    val cursor: Cursor[Flight] = flights.find(query).cursor[Flight]
    val futureFlightsList: Future[List[Flight]] = cursor.collect[List]()
    Await.result(futureFlightsList, timeout)
  }

  //TODO: Avoid using await and return directly futures with Action.async
  def updateStatus(id: String, status: String) = {
    val flightToUpdate = flights.find(Json.obj("id" -> id)).cursor[JsObject]
    val futureFlightsList = flightToUpdate.collect[List]()
    val statusUpdate = (__ \ "status").json.
      update(__.read[JsString].map { o => JsString(status) })
      
    Await.result(futureFlightsList, timeout).foreach(_.transform(statusUpdate) match {
      case JsSuccess(updated, path) => Await.result(flights.save(updated), timeout)
      case _ => Logger.error("Unable to update the flight with id ${id}")
    })
  }

  def delete(id: String) = {
    Await.result(flights.remove(Json.obj("id" -> id)), timeout)
  }
}