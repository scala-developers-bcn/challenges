package repositories

import model.Flight

trait FlightsRepository {
  def insert(f: Flight)
  def flightsTo(id: String, from: Long, to: Long): Iterable[Flight]
  def flightsFrom(id: String, from: Long, to: Long): Iterable[Flight]
  def updateStatus(id: String, status: String)
  def delete(id: String)
}