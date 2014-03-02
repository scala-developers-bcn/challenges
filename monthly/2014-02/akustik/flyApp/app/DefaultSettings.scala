import play.api.GlobalSettings
import play.api.Logger
import controllers.Flights
import repositories._
import scala.concurrent.{ Future, Await }
import scala.concurrent.duration.Duration

object DefaultSettings extends GlobalSettings {
  
  val timeout = Duration(1000, "millis")

  val flightsRepo = new MongoDBFlightsRepository
  
  //FIXME: where to ensure indexes
  //Await.result(flightsRepo.ensureIndexes, timeout)
  
  val flightsController = new Flights(flightsRepo)

  val classOfFlights = classOf[Flights]

  override def onStart(app: play.api.Application) {
    Logger.info("Init default settings")
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    controllerClass match {
      case `classOfFlights` => flightsController.asInstanceOf[A]
      case _ => {
        throw new IllegalArgumentException(s"Controller of class ${controllerClass} not available")
      }
    }
  }

}