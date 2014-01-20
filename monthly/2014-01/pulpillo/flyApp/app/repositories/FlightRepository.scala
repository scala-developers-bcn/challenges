package repositories

import java.util.concurrent.atomic.AtomicLong
import models.Airport
import models.Flight
import scala.collection.concurrent.TrieMap
import com.github.nscala_time.time.Imports._

trait FlightRepositoryComponent {

  val flightRepository: FlightRepository

  trait FlightRepository {

    def createFlight(flight : Flight) : Flight

    def updateFlight(flight: Flight)

    def delete(id : Long)

    def find(f:Flight => Boolean): Option[List[Flight]]

    def tryFindById(id:Long): Option[Flight]

  }
}

trait FlightRepositoryComponentImpl extends FlightRepositoryComponent {

 override val flightRepository = new FlightRepositoryImpl

 class FlightRepositoryImpl extends FlightRepository {

   val flights = new TrieMap[Long,Flight]
   val idFlight = new AtomicLong(0)

   val airports = Vector(Airport(1,"Madrid"),
     Airport(2,"Barcelona"),
     Airport(3,"Milan"),
     Airport(4,"New York"),
     Airport(5,"Lisboa"),
     Airport(6,"Sevilla"),
     Airport(7,"Bilbao"),
     Airport(8,"Granada"),
     Airport(9,"Tarragona"),
     Airport(10,"Paris"))

   private def randomInt(implicit n: Int = 10) = (Math.random * n).toInt

   private def getRandomAirport() = {
     airports(randomInt)
   }

   private def generateFlights() = {
     for(i <- 0L until 100L){
       createFlight(Flight(Option(i),getRandomAirport(),DateTime.now,getRandomAirport(),DateTime.now + 2.hours,"STATUS"))
     }
   }

   //Generate dummy flights
   generateFlights()

   override def createFlight(flight : Flight) : Flight = {
     val newId = idFlight.incrementAndGet()
     val createdFlight = flight.copy(id = Option(newId))
     flights.put(newId, createdFlight)
     createdFlight
   }

   override def updateFlight(flight: Flight) = {
     flights.put(flight.id.get, flight)
   }

   override def delete(id : Long) = {
     flights.remove(id)
   }

   override def find(f: Flight => Boolean): Option[List[Flight]] = {
     Option(flights.values.toList.filter(f))
   }

   override def tryFindById(id:Long): Option[Flight] = {
     flights.get(id)
   }
 }

}