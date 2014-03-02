package repositories

import model.Flight
import scala.concurrent.Future

trait FlightsRepository {
  
  /** Inserts a new flight
   *  @param f A flight
   *  @return A future boolean for success, false otherwise
   */
  def insert(f: Flight): Future[Boolean]
  
  /** Finds flights that go to a given location
   */
  def flightsTo(id: String, from: Long, to: Long): Future[List[Flight]]
  
  /** A list of flights that come from a given location
   */
  def flightsFrom(id: String, from: Long, to: Long): Future[List[Flight]]
  
  /** Updates the status of a given flight
   *  @param id A flight identifier, i.e. VL1234
   *  @return A future boolean for success, false otherwise
   */
  def updateStatus(id: String, status: String): Future[Boolean]
  
  /** Deletes flights with the given id
   *  @param id A flight identifier, i.e. VL1234
   *  @return A future boolean for success, false otherwise
   */
  def delete(id: String): Future[Boolean]
}