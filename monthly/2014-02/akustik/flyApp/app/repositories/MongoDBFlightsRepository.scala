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
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.api.indexes._
import scala.concurrent.duration.Duration
import scala.concurrent.{ Future, Await }
import reactivemongo.core.commands.Ascending
import reactivemongo.core.commands.LastError

/**
 * Check this out:
 *   coast-to-coast: http://www.playframework.com/documentation/2.1.3/ScalaJsonTransformers
 *   reactive mongo for play: https://github.com/ReactiveMongo/Play-ReactiveMongo
 *   http://docs.mongodb.org/manual/tutorial/query-documents/
 *   http://reactivemongo.org/releases/0.9/api/index.html#reactivemongo.api.package
 */
class MongoDBFlightsRepository extends FlightsRepository {

  case class FlightWithId(_id: String)

  val timeout = Duration(1000, "millis")

  private def flights: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("flights")
  
  def ensureIndexes: Future[Boolean] = {
    ensureIndex(List(("id", IndexType.Ascending)), unique = true)
  }
 
  def ensureIndex(
    key: List[(String, IndexType)],
    name: Option[String] = None,
    unique: Boolean = false,
    background: Boolean = false,
    dropDups: Boolean = false,
    sparse: Boolean = false,
    version: Option[Int] = None,
    options: BSONDocument = BSONDocument()) = {
    flights.indexesManager.ensure(Index(key, name, unique, background, dropDups, sparse, version, options))
  }
  
  implicit val flightFormat = Json.format[Flight]

  def insert(f: Flight) = flights.insert(f).map(_.ok)

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
  
  def flightsTo(id: String, from: Long, to: Long): Future[List[Flight]] = {
    val query = addToClause(addFromClause(Json.obj("to" -> id), from), to)
    flights.find(query).cursor[Flight].collect[List]()
  }

  def flightsFrom(id: String, from: Long, to: Long): Future[List[Flight]] = {
    val query = addToClause(addFromClause(Json.obj("from" -> id), from), to)
    flights.find(query).cursor[Flight].collect[List]()
  }
  
  def updateStatus(id: String, status: String) = {
    flights.update(Json.obj("id" -> id), 
      Json.obj("$set" -> Json.obj("status" -> status)), 
      multi = true).map(_.ok)
  }

//  Old version: Getting n jsobjects and updating them.
//  def updateStatus(id: String, status: String) = {
//    val flightToUpdate = flights.find(Json.obj("id" -> id)).cursor[JsObject]
//    val futureFlightsList = flightToUpdate.collect[List]()
//    val statusUpdate = (__ \ "status").json.
//      update(__.read[JsString].map { o => JsString(status) })
//    val updates = futureFlightsList.flatMap(flightsToBeUpdated => {
//      val updateResults = flightsToBeUpdated.map(_.transform(statusUpdate)).map(_ match {
//        case JsSuccess(updated, path) => flights.save(updated).map(_.ok)
//        case _ => Future{ false }
//      })
//       Future.sequence(updateResults)
//    })
//    updates.map(r => r.foldRight(true)((a, b) => a && b))
//  }

  def delete(id: String) = flights.remove(Json.obj("id" -> id)).map(_.ok)
}