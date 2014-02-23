package repositories

import model.Flight

import play.api.Logger
import play.api.libs.json._
import play.modules.reactivemongo._
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.Play.current

class MongoDBFlightsRepository extends FlightsRepository {

  private def flights: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("flights")

  def insert(f: Flight) = {
  	val json = Json.obj(
      "name" -> "guillem",
      "age" -> 22,
      "created" -> new java.util.Date().getTime())

  	flights.insert(json).map(lastError => Logger.info("lastError = " + lastError))
  }

  def flightsTo(id: String, from: Long, to: Long): Iterable[Flight] = List()
  def flightsFrom(id: String, from: Long, to: Long): Iterable[Flight] = List()
  def updateStatus(id: String, status: String) = {}
  def delete(id: String) = {}
}