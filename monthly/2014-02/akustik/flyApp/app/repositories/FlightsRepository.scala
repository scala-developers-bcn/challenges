package repositories

import model.Flight
import scala.concurrent.Future

trait FlightsRepository {
  
  /** Inserts a new flight
   *  @param f A flight
   *  @return A future boolean for success, false otherwise
   */
  def insert(f: Flight): Future[Boolean]
  
  def flightsTo(id: String, from: Long, to: Long): Iterable[Flight]
  def flightsFrom(id: String, from: Long, to: Long): Iterable[Flight]
  def updateStatus(id: String, status: String)
  
  /** Deletes flights with the given id
   *  @param id A flight identifier, i.e. VL1234
   *  @return A future boolean for success, false otherwise
   */
  def delete(id: String): Future[Boolean]
}