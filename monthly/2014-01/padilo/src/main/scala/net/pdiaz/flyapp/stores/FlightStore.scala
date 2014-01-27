package net.pdiaz.flyapp.stores

import java.util.Date
import scala.collection._
import com.github.nscala_time.time.Imports._

// Results
trait StoreResult
case object StoreDone extends StoreResult
case object DataAlreadyExists extends StoreResult

trait ModifyResult
case object ModifyDone extends ModifyResult
case object DataNotFound extends ModifyResult

// Flight Status
object FlightStatus extends Enumeration {
  type FlightStatus = Value
  val Delayed, Cancelled, OnTime = Value
}

case class Flight(departure: DateTime, arrival: DateTime, from: String, to: String, status: FlightStatus.Value)

trait FlightStore {
  def listFilter(toFilter: Option[String], fromFilter: Option[String]): List[(String, Flight)]
  def list(): List[(String, Flight)]
  def store(key: String, flight: Flight): StoreResult
  def update(key: String, flight: Flight): ModifyResult
  def delete(key: String): ModifyResult

  def putDefaultData() {
    store("1", Flight(DateTime.now.hour(9).minute(5).second(0), DateTime.now.hour(10).minute(0).second(0), "Barcelona", "Madrid", FlightStatus.OnTime))
    store("2", Flight(DateTime.now.hour(10).minute(20).second(0), DateTime.now.hour(11).minute(15).second(0), "Madrid", "Barcelona", FlightStatus.Delayed))
    store("3", Flight(DateTime.now.hour(16).minute(10).second(0), DateTime.now.hour(17).minute(0).second(0), "Zaragoza", "Coruña", FlightStatus.Cancelled))
    store("4", Flight(DateTime.now.hour(0).minute(20).second(0), DateTime.now.hour(12).minute(10).second(0), "Coruña", "Madrid", FlightStatus.OnTime))
    store("5", Flight(DateTime.now.hour(12).minute(30).second(0), DateTime.now.hour(13).minute(45).second(0), "Barcelona", "Zaragoza", FlightStatus.OnTime))
  }
}

class FlightMemStore extends FlightStore {
  private val dataMap = new mutable.HashMap[String, Flight]

  def store(key: String, flight: Flight): StoreResult =
    if (dataMap.contains(key)) {
      DataAlreadyExists
    } else {
      dataMap.put(key, flight)
      StoreDone
    }

  def get(key: String): Option[Flight] = {
    dataMap.get(key)
  }

  private def ifExists(key: String)(doThings: => Unit) = {
    if (dataMap.contains(key)) {
      doThings
      ModifyDone
    } else DataNotFound
  }

  def update(key: String, flight: Flight): ModifyResult = {
    ifExists(key) {
      dataMap.update(key, flight)
    }
  }
  def delete(key: String): ModifyResult = {
    ifExists(key) {
      dataMap.remove(key)
    }
  }

  def list(): List[(String, Flight)] = {
    listFilter(None, None)
  }

  def listFilter(toFilter: Option[String], fromFilter: Option[String]): List[(String, Flight)] = {
    def filterFunc(option: Option[String]) = option match {
      case Some(att) => {
        (flightAtt: String) =>
          {
            att == flightAtt
          }
      }
      case None => {
        (_: String) => true
      }
    }

    val toFilterFunc = filterFunc(toFilter)
    val fromFilterFunc = filterFunc(fromFilter)

    def iterable = for {
      entry <- dataMap
      flight = entry._2
      key = entry._1
      if (toFilterFunc(flight.to) && fromFilterFunc(flight.from) && DateTime.now <= flight.departure)
    } yield entry

    iterable.toList
  }
}